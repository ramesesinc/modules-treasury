package com.rameses.gov.treasury.report.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;

class RemittanceRCDFundReportModel extends ReportController {

    @Binding
    def binding;

    @Service("RemittanceRCDReportService")
    def svc;
    
    def fund;
    def fundlist;
     
    
    String title = "Remittance RCD By Fund";
    String reportpath =  "com/rameses/enterprise/treasury/printforms/";
    String reportName = reportpath + 'remittance_fund.jasper' 
    
    def initReport() {
        fundlist = svc.getFundlist( entity.objid );
        fund = (fundlist ? fundlist.first() : [:]); 
        return preview();
    }
    
    void setFund( def fund) {
        this.fund = fund;
        
        preview();
        binding.refresh() ;
    }
    
    def getReportData(){
        def rdata = svc.getReport([ objid: entity.objid, fund: fund ]); 
        if ( rdata ) { 
            def totalcr = (rdata.totalcr ? rdata.totalcr : 0.0);
            def totalcheck = (rdata.totalcheck ? rdata.totalcheck : 0.0);
            
            rdata.totalcr = totalcr; 
            rdata.totalcheck = totalcheck;
            rdata.totalnoncash = totalcheck + totalcr;
            rdata.totalcash = rdata.amount - rdata.totalnoncash;
            
            rdata.remittancedate = rdata.controldate;
            rdata.txnno = rdata.controlno; 
        } 
        return rdata;
    }    
} 
