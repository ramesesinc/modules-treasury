package com.rameses.gov.treasury.report.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;
        
class DepositVoucherRCDByReportModel extends ReportController  {
        
    @Binding
    def binding;
        
    @Service("DepositVoucherRCDReportService")
    def svc;

    String title = "Report on Collections and Deposits (RCD) By Fund";
    String reportpath = "com/rameses/gov/treasury/depositvoucher/report/rcd/";
    String reportName = reportpath + 'main_byfund.jasper';

    def reportParams;
    def fundlist;
    def fund;

    def initReport() {
        reportParams = [:]; 
        
        def resp = svc.initReport([ depositvoucherid: entity.objid ]); 
        fundlist = resp.funds; 
        if ( fundlist ) setFund( fundlist.first() );
    }
            
    void setFund( fund ){ 
        this.fund = fund; 
        report.viewReport(); 

        if ( binding ) binding.refresh(); 
    }
            
    def getReportData(){ 
        if( !fund?.objid )
            throw new Exception('Please select a specific Fund'); 
            
        return svc.getRCDByFund([ depositvoucherid: entity.objid, fund: fund ]); 
    }
            
    Map getParameters() {
        return reportParams;
    } 
            
    SubReport[] getSubReports() {
        return [ 
            new SubReport("liquidations", reportpath + "liquidations.jasper"),
            new SubReport("remittances", reportpath + "remittances.jasper"),
            new SubReport("afserials", reportpath + "afserials.jasper"),
            new SubReport("afnonserials", reportpath + "afnonserials.jasper")
        ] as SubReport[]; 
    } 
}