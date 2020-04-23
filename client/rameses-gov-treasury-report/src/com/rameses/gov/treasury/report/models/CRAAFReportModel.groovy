package com.rameses.gov.treasury.report.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;

class CRAAFReportModel extends AsyncReportController {
    
    @Service('CRAAFReportService') 
    def svc;
    
    String title = 'Consolidated Report of Accountability for Accountable Forms';
    String reportpath = 'com/rameses/gov/treasury/report/craaf/';
    String reportName = reportpath + 'craaf.jasper';
    
    def data;
    
    def initReport(){
        return 'default';
    }
    
    void buildReportData(entity, asyncHandler) { 
        svc.generateCraafData(entity, asyncHandler);
    }

    void buildResult( res ) {
        data = res; 
    }
    
    def formControl = [
        getFormControls: {
            return [
                new FormControl( "integer", [caption:'Year', name:'entity.year', required:true, preferredSize:'100,19', captionWidth:100]), 
                new FormControl( "combo", [caption:'Month', name:'entity.month', items:'months', expression:'#{item.caption}', preferredSize:'100,19', captionWidth:100]) 
            ]; 
        }
    ] as FormPanelModel;
   
   
    Map getParameters(){
        return data.info; 
    }
    
    List getMonths(){
        return dtSvc.getMonths();
    }
}
