package com.rameses.enterprise.treasury.util; 

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.reports.*;

public class CashReceiptPrintUtil { 

    def binding;
    
    def onbeforePrint;
    
    boolean showPrintDialog = true;
    
    def reprint_dev_handle;
    boolean reprint_dev_mode;
    
    public void reprint( template_name, rct ) {
        try {
            reprint_dev_handle = null;
            reprint_dev_mode = ReportUtil.isDeveloperMode(); 
            
            print( template_name, rct ); 

            if ( reprint_dev_handle ) {
                def m = [ report: reprint_dev_handle, title: 'Cash Receipt: Reprint' ];
                def op = Inv.lookupOpener('report:preview', m);
                op.target = 'self';
                binding.fireNavigation( op ); 
            } 
        } 
        finally {
            reprint_dev_mode = false; 
            reprint_dev_handle = null; 
        }        
    }
    
    public void printDetails( template_name, rct ) {
        try {
            reprint_dev_handle = null;
            reprint_dev_mode = ReportUtil.isDeveloperMode(); 
            
            print( template_name, rct ); 

            if ( reprint_dev_handle ) {
                def m = [ report: reprint_dev_handle, title: 'Cash Receipt Details' ];
                def op = Inv.lookupOpener('report:preview', m);
                op.target = 'self';
                binding.fireNavigation( op ); 
            } 
        } 
        finally {
            reprint_dev_mode = false; 
            reprint_dev_handle = null; 
        }        
    }

    public void print( template_name, entity ) {
        if ( onbeforePrint ) { 
            def b = onbeforePrint(); 
            if ( b.toString() == 'false') {
                return; 
            }
        }
        
        def receipts = entity.receipts;
        if ( receipts == null ) receipts = [ entity ]; 
        
        boolean haserrors = false; 
        receipts.each{ rct-> 
            if ( haserrors ) return; 
            if ( reprint_dev_handle ) return; 
            
            def op = Inv.lookupOpener( template_name, [ reportData: rct ]);
            def opHandle = op.handle;
            def reportHandle = findReportModel( opHandle ); 
            if ( reportHandle == null ) {
                haserrors = true; 
                MsgBox.alert("Report Handle for " + name + " must be a ReportModel " );
                return; 
            } 

            reportHandle.viewReport(); 
            
            if ( reprint_dev_mode ) {
                reprint_dev_handle = reportHandle; 
            }
            else { 
                ReportUtil.print(reportHandle.report, showPrintDialog); 
            }
        }
    }
    
    private def findReportModel( o ) {
        if ( o == null ) return null; 
        else if (o instanceof ReportModel ) return o; 
        else if (o instanceof Opener) return findReportModel( o.handle ); 
        
        if ( o.metaClass.respondsTo(o, 'viewReport' )) {
            def oo = o.viewReport(); 
            return findReportModel( oo ); 
        } else if ( o.metaClass.hasProperty(o, 'report' )) {
            return findReportModel( o.report ); 
        } else {
            return null; 
        }
    }    
} 