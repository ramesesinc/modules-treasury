package com.rameses.gov.treasury.report.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;

class AFInventoryReportModel extends AsyncReportController {

    @Script('ReportPeriod')
    def reportPeriod;
    
    @Service('AFInventoryReportService') 
    def svc;
    
    String title = 'AF Inventory Report';
    String reportpath = 'com/rameses/gov/treasury/report/';
    String reportName = reportpath + 'afinventoryreport.jasper';
    
    def data; 
        
    def initReport(){
        return 'default'
    }
    
    void buildReportData(entity, asyncHandler) { 
        svc.generateReport(entity, asyncHandler);
    }

    void buildResult( res ) {
        data = res; 
    }
    
    def formControl = [
        getFormControls: {
            return [
                new FormControl( "integer", [caption:'Year', name:'entity.year', required:true, preferredSize:'100,19', captionWidth:100]), 
                new FormControl( "combo", [caption:'Month', name:'entity.month', required:true, items:'months', expression:'#{item.title}', preferredSize:'100,19', captionWidth:100])
            ];
        }
    ] as FormPanelModel;
      
    Map getParameters() { 
        return data.info; 
    }

    List getMonths(){
        return (List) reportPeriod.getMonths();
    }
}
