package com.rameses.gov.treasury.report.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;
        
class DepositVoucherRCDFundReportModel extends ReportController  {
        
    @Binding
    def binding;
        
    @Service("DepositVoucherRCDReportService")
    def svc;

    String title = "Report on Collections and Deposits (RCD) By Fund";
    String reportpath = "com/rameses/gov/treasury/depositvoucher/report/rcd/";
    String reportName = reportpath + 'main_byfund.jasper';

    def reportParams;
    def funds;
    def fund;
    
    def template; 
    def templates = [
        [objid: 'a', title: 'Template - A'],
        [objid: 'b', title: 'Template - B']
    ];

    def initReport() {
        this.reportParams = [:]; 
        this.template = templates.first(); 
        
        def resp = svc.initReport([ depositvoucherid: entity.objid ]); 
        funds = resp.funds; 
        if ( funds ) {
            setFund( funds.first() );
        }
    }
            
    void setFund( fund ){ 
        this.fund = fund; 
        report.viewReport(); 
        if ( binding ) binding.refresh(); 
    }
    
    void setTemplate( template ) {
        this.template = template;
        report.viewReport(); 
        if ( binding ) binding.refresh(); 
    }
            
    def getReportData(){ 
        def m = [ depositvoucherid: entity.objid, fund: fund, template: template ]; 
        if ( !m.fund?.objid ) m.fund = [ objid: 'all']; 
        
        return svc.getRCDByFund( m ); 
    }
            
    Map getParameters() {
        def templateid = template?.objid; 
        reportParams.TEMPLATE = ( templateid ? templateid : 'a'); 
        return reportParams;
    } 

    SubReport[] getSubReports() {
        return [ 
            new SubReport("collectiontypes_a", reportpath + "collectiontypes_a.jasper"),
            new SubReport("collectiontypes_b", reportpath + "collectiontypes_b.jasper"),
            new SubReport("remittances", reportpath + "remittances.jasper"),
            new SubReport("afserials", reportpath + "afserials.jasper"),
            new SubReport("afnonserials", reportpath + "afnonserials.jasper")
        ] as SubReport[]; 
    } 
}