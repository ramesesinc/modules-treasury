package com.rameses.gov.treasury.report.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;

class RAAFReportModel extends ReportController {

    @Script('User')
    def user;
    
    @Service('RAAFReportService') 
    def svc;
    
    @Service('UserRoleService')
    def userroleSvc;
    
    @Service('DateService')
    def dtSvc; 

    String title = 'Report of Accountability for Accountable Forms'
    String reportpath = 'com/rameses/gov/treasury/report/raaf/'
    String reportName = reportpath + 'raaf.jasper'
    
    def data;
    def tag;
    
    List collectors = []; 
    List months = []; 
    
    def formControl = [
        getControlList: {
            def list = []; 
            list << [type:"integer", caption:'Year', name:'entity.year', required:true, preferredSize:'100,20', captionWidth:100]; 
            list << [type:"combo", caption:'Month', name:'entity.month', required:true, items:'months', expression:'#{item.caption}', preferredSize:'100,20', captionWidth:100]; 
            list << [type:"combo", caption:'Collector', name:'entity.collector', required:true, items:'collectors', expression:'#{item.fullname}', preferredSize:'0,20', captionWidth:100]; 
            list << [type:"label", caption:'', expression:'', preferredSize:'0,20']; 
            list << [type:"checkbox", caption:'', name:'entity.condensed', text:' Condensed', captionWidth:100]; 
            return list; 
        }
    ] as FormPanelModel; 

    def doInit( invoker ) {
        tag = invoker?.properties?.tag; 
        
        def users = userroleSvc.getUsers([ domain:'TREASURY', roles:'COLLECTOR' ]).collect{[ 
                objid:it.objid, name:it.name, title:it.title, 
                fullname: it.lastname +', '+ it.firstname +' '+ (it.middlename? it.middlename: '')  
            ]}.unique(); 
        
        if ( tag == 'all' ) { 
            collectors = users; 
        } else { 
            entity.collector = users.find{( it.objid == user.env.USERID )}
            if ( entity.collector ) collectors = [ entity.collector ];  
        } 
        
        months = dtSvc.getMonths(); 
        
        def arr = dtSvc.getServerDate().toString().split("-");
        entity.year = Integer.parseInt( arr[0] );

        int monthidx = Integer.parseInt( arr[1]); 
        entity.month = months.find{( it.index == monthidx )} 
        return init(); 
    } 
    
    def getReportData(){
        data = svc.getReportData(entity);
        return data.items
    }
    
    Map getParameters(){
        return data.info
    }
} 