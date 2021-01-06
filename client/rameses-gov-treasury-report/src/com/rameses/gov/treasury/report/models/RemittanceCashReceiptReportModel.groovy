package com.rameses.gov.treasury.report.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;

class RemittanceCashReceiptReportModel extends ReportController  {

    @Binding
    def binding;

    @Service("RemittanceReportService")
    def svc;
    
    def fund;
    def fundlist;
     
    String reportpath =  "com/rameses/gov/treasury/remittance/report/";
    String reportName = reportpath + 'detailedreportbycashreceipts.jasper';
    String title = "Remittance Report By Receipts";
    

    def initReport() {
        fund = [:];
        fundlist = svc.getFundlist( entity.objid ); 
        return preview();
    }
    
    void setFund( def fund ) {
        this.fund = fund;
        
        preview();
        binding.refresh();
    }

    def getReportData(){
        return svc.generateReportByCashReceipts( entity, fund ); 
    }
    
    SubReport[] getSubReports() {
        return [ 
            new SubReport("CASHRECEIPTITEMS", reportpath + "detailedreportbycashreceiptsitems.jasper"),
            new SubReport("FUNDSUMMARY", reportpath + "reportbyfundsummary.jasper")
        ] as SubReport[];      
    }
    
    Map getParameters() {
        def params = [:]
        params.SUBTITLE = 'SUMMARY OF COLLECTION BY RECEIPTS';
        return params;
    }    
} 
