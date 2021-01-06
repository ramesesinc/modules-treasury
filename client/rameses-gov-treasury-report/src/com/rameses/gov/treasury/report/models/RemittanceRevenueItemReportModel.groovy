package com.rameses.gov.treasury.report.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;

class RemittanceRevenueItemReportModel extends ReportController {

    @Binding
    def binding;

    @Service("RemittanceReportService")
    def svc;
    
    def fund;
    def fundlist;
     
    String reportpath =  "com/rameses/gov/treasury/remittance/report/";
    String reportName = reportpath + 'reportbyrevenueitem.jasper';
    String title = "Remittance Report By Revenue Item";
    
    def initReport() {
        fund = [:];
        fundlist = svc.getFundlist( entity.objid );
        return preview();
    }
    
    void setFund( def fund) {
        this.fund = fund;
        
        preview();
        binding.refresh();
    }
        
    def getReportData(){
        return svc.generateReportByRevenueItem( entity, fund );
    }
    
    SubReport[] getSubReports() {
        return [ 
            new SubReport("ReportSummary", reportpath + "reportbyrevenueitemsummary.jasper"),
            new SubReport("BrgyShareSummary", reportpath + "reportbyrevenueitemsummary_share.jasper")
        ] as SubReport[];      
    }
    
    Map getParameters(){
        def params = [:];
        params.SUBTITLE = 'SUMMARY OF COLLECTION BY ITEM ACCOUNT';
        if ( fund ) { 
            params.FUND_TITLE = '( ' + fund.title + ' )';
        }
        return params;
    }
} 
