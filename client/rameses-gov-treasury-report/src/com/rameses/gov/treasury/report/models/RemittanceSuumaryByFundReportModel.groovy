package com.rameses.gov.treasury.report.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;

class RemittanceSuumaryByFundReportModel extends ReportController {

    @Service("RemittanceReportService")
    def svc;

    String title = "Remittance: Summary of Collections By Fund"; 
    String reportpath = "com/rameses/gov/treasury/remittance/report/"; 
    String reportName = reportpath + 'collection_summary_byfund_crosstab.jasper'; 

    def result = [:]; 

    def getReportData(){
       result = svc.generateAbstractSummaryOfCollectionByFund( entity ); 
       return result.reportdata; 
    }

    SubReport[] getSubReports() {
         return null;    
    }

    Map getParameters() { 
        def header = result.header; 
        if ( !header ) header = [:];

        return header; 
    } 
}  
