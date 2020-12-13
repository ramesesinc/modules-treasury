package com.rameses.gov.treasury.report.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;

class CRAAFReportModel extends AsyncReportController {
    
    @Script('ReportPeriod')
    def reportPeriod;
    
    @Service('CRAAFReportService') 
    def svc;
    
    String title = 'Consolidated Report of Accountability for Accountable Forms';
    String reportpath = 'com/rameses/gov/treasury/report/craaf/';
    String reportName = reportpath + 'craaf.jasper';
    
    def data;
    def aflist;
    
    def initReport(){
        def resp = svc.initReport([:]); 
        aflist = resp.aflist;
        return 'default';
    }
    
    void buildReportData(entity, asyncHandler) { 
        svc.generateCraafData(entity, asyncHandler);
    }

    void buildResult( res ) {
        data = res; 
    }
    
    def formControl = [
        getControlList: {
            def list = []; 
            list << [type:"integer", caption:'Year', name:'entity.year', required:true, preferredSize:'100,20', captionWidth:100]; 
            list << [type:"combo", caption:'Month', name:'entity.month', required:true, items:'months', expression:'#{item.title}', preferredSize:'100,20', captionWidth:100]; 
            list << [type:"combo", caption:'AF', name:'entity.af', items:'aflist', expression:'#{item.objid} - #{item.title}', preferredSize:'0,20', captionWidth:100]; 
            list << [type:"label", caption:'', expression:'', preferredSize:'0,20']; 
            list << [type:"checkbox", caption:'', name:'entity.condense_saled', text:' Condense SALE', captionWidth:100]; 
            list << [type:"checkbox", caption:'', name:'entity.condense_consumed', text:' Condense CONSUMED', captionWidth:100]; 
            return list; 
        }        
    ] as FormPanelModel;
   
    Map getParameters(){
        return data.info; 
    }
    
    List getMonths(){
        return (List) reportPeriod.getMonths();
    }
}
