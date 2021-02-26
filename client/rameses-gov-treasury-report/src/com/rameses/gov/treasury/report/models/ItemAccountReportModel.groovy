package com.rameses.gov.treasury.report.models;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;

class ItemAccountReportModel extends AsyncReportController {

    @Script('ReportPeriod') 
    def periodUtil; 

    @Script('User') 
    def user; 

    @Service('ItemAccountReportService') 
    def svc;
    
    String title = 'Report of Item Accounts'
    String reportPath = 'com/rameses/gov/treasury/report/jasper/';
    
    def data;
    def itemAccts; 
    def selectedItem;
    def reportParam = [:]; 
    
    def templateTypes = [
        [objid: 'detail', name: 'Details', report: 'report_itemaccount.jasper'],
        [objid: 'summary',  name: 'Summary', report: 'report_itemaccount_summary.jasper']
    ]; 
    
    def postingTypes = [
        [objid: 'liquidation', name: 'By Liquidation Date'],
        [objid: 'remittance',  name: 'By Remittance Date']
    ]; 
    
    
    def initReport(){
        def outcome = super.initReport(); 
        
        itemAccts = []; 
        entity.template = templateTypes[0]; 
        entity.period = periodUtil.types.find{( it.type == 'monthly' )}?.type; 
        return outcome; 
    }
    
    String getReportName() {
        return reportPath + entity.template?.report.toString();
    }
    
    void buildReportData(entity, asyncHandler) { 
        if ( !itemAccts ) throw new Exception('Please specify at least one Item Account'); 
        
        def m = periodUtil.build( entity.period, entity ); 
        
        reportParam.clear(); 
        reportParam.PERIOD_TITLE = buildPeriodTitle( entity.period, m.startdate, m.enddate ); 
        
        def dateBean = new com.rameses.util.DateBean( m.enddate ); 
        m.enddate = periodUtil.format( dateBean.add("1d"), 'yyyy-MM-dd' ); 
        
        entity.enddate = m.enddate; 
        entity.startdate = m.startdate;
        entity.accounts = itemAccts.collect{ it.objid }.findAll{( it )} 
        svc.getReport(entity, asyncHandler);
    } 
    
    void buildResult( data ) {
        reportParam.PREPAREDBY_NAME = user.env.FULLNAME; 
        reportParam.PREPAREDBY_TITLE = user.env.JOBTITLE; 
    }

    Map getParameters() { 
        return reportParam; 
    } 
    
    private String buildPeriodTitle( period, startdate, enddate ) { 
        def type = period.toString(); 
        startdate = toDate( startdate ); 
        enddate = toDate( enddate ); 
        
        if ( type == 'yearly' ) {
            def buff = new StringBuilder(); 
            buff.append( periodUtil.format( startdate, 'MMMMM' )).append(' - '); 
            buff.append( periodUtil.format( enddate, 'MMMMM, yyyy' )); 
            return buff.toString().toUpperCase(); 
        }
        else if ( type == 'quarterly' ) {
            def buff = new StringBuilder(); 
            buff.append( periodUtil.format( startdate, 'MMMMM' )).append(' - '); 
            buff.append( periodUtil.format( enddate, 'MMMMM, yyyy' )); 
            return buff.toString().toUpperCase(); 
        }
        else if ( type == 'monthly' ) {
            return periodUtil.format( startdate, 'MMMMM yyyy' ).toUpperCase();  
        }
        else if ( type == 'daily' ) {
            return periodUtil.format( startdate, 'MMMMM dd, yyyy' ).toUpperCase();  
        }
        else if ( type == 'range' ) {
            def buff = new StringBuilder(); 
            buff.append( periodUtil.format( startdate, 'MMM/dd/yyyy' )).append(' - '); 
            buff.append( periodUtil.format( enddate, 'MMM/dd/yyyy' )); 
            return buff.toString().toUpperCase(); 
        }
        return null; 
    } 
    
    private def toDate( value ) {
        if ( !value ) return null; 
        if ( value instanceof java.util.Date ) return value; 
        
        try {
            return java.sql.Timestamp.valueOf( value ); 
        } catch(Throwable t) {;} 

        try {
            return java.sql.Date.valueOf( value ); 
        } catch(Throwable t) {
            return null; 
        } 
    }
    
        
    def itemAcctListHandler = [
        isMultiSelect: { 
            return true; 
        }, 
        fetchList: { o-> 
            return itemAccts; 
        },
        afterSelectionChange: { 
            if ( binding ) {
                binding.notifyDepends('selectedItem'); 
            }
        }
    ] as DataListModel; 
    
    boolean isHasSelectedItems() {
        return ( itemAcctListHandler.selectedValue ? true : false ); 
    }
    
    void removeItem() {
        if ( !itemAcctListHandler.selectedValue ) return; 
        
        itemAcctListHandler.selectedValue.each{
            itemAccts.remove( it ); 
        }
        itemAcctListHandler.reload(); 
    }
    
    void addItem() { 
        def param = [ multiSelectEnabled: true ]; 
        param.beforeQueryHandler = { map-> 
            map.exclude = itemAccts.collect{ it.objid }.findAll{( it )} 
        }
        param.onselect = { list-> 
            list.each{ o-> 
                if ( !itemAccts.contains( o )) { 
                    itemAccts << o; 
                }
            }
            itemAcctListHandler.reload(); 
        }
        Modal.show('itemaccount:lookup', param); 
    }
}