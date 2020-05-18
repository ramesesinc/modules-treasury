package com.rameses.gov.treasury.report.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;
        
class DepositVoucherFundReportModel extends ReportController  {
        
    @Binding
    def binding;
        
    @Service("DepositVoucherReportService")
    def svc;

    String title = "Deposit Voucher Report By Fund";

    def reportParams;
    def funds;
    def fund;
    
    def initReport() {
        this.reportParams = [:]; 
        
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
    
    def getReportData(){ 
        def m = [ depositvoucherid: entity.objid, fund: fund ]; 
        if ( !m.fund?.objid ) m.fund = [ objid: 'all']; 
        
        return svc.getReportByFund( m ); 
    }
            
    Map getParameters() {
        return reportParams;
    } 

    String reportpath = "com/rameses/gov/treasury/depositvoucher/report/";
    String reportName = reportpath + 'reportbyfund.jasper';
    
    SubReport[] getSubReports() {
        return [ 
            new SubReport("receipts", reportpath + "reportbyfunditem.jasper"),
            new SubReport("acctsummaries", reportpath + "reportbyfundsummary.jasper")
        ] as SubReport[]; 
    } 
}