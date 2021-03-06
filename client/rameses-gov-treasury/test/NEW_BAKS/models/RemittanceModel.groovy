package com.rameses.enterprise.treasury.models;

import com.rameses.osiris2.common.*;
import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import java.rmi.server.*;
import com.rameses.osiris2.client.*;
import com.rameses.util.*;
import javax.swing.*;
import com.rameses.io.*;
import com.rameses.seti2.models.*;

class RemittanceModel  extends CrudFormModel { 

    @Service("RemittanceService")
    def service;

    @Service("RemittanceImportExportService")
    def exportSvc;

    final def numFormatter = new java.text.DecimalFormat("#,##0.00"); 
    
    def selectedFund;
    def remittancedate;
    boolean captureMode = false;
    String schemaName = "remittance";
    
    @FormTitle
    public String getTitle() {
        if( captureMode ) {
            return "Capture Remittance";
        } 
        else if( entity?.state.toString().equalsIgnoreCase('DRAFT') ) {
            return "New Remittance"; 
        } 
        else { 
            return "Remittance " + entity.txnno;
        } 
    }

    @FormId
    public String getFormId() {
        return entity.objid;
    }

    public void afterCreate() { 
        entity = service.init([ remittancedate: remittancedate ]);  
        mode = "read";
    } 

    def capture() { 
        captureMode = true; 
        return "capture"; 
    }    
    
    boolean isViewReportAllowed() { 
        if ( entity.state.toString().toUpperCase() == 'DRAFT' ) {
            return false; 
        } 
        return super.isViewReportAllowed(); 
    } 

    
    boolean isCanPrintReport() { 
        return ( entity.state != 'DRAFT' ); 
    } 
    
    def getPrintFormData() {  
        return service.openForReport([ objid:entity.objid ]); 
    } 
        
    def popupReports(def inv) {
        def popupMenu = new PopupMenuOpener();
        def list = InvokerUtil.lookupOpeners( inv.properties.category, [entity:entity] );
        list.each{
            popupMenu.add( it );
        }
        return popupMenu;
    }
        
    def delete() { 
        if (MsgBox.confirm("You are about to delete this transaction. Proceed?")) {
            persistenceSvc.removeEntity([ _schemaname:schemaName, objid: entity.objid ]); 
            try { 
                return "_close"; 
            } finally {
                try {
                    if (caller) caller?.reload(); 
                } catch(Throwable t){;}
            }
        } 
        return null; 
    } 

    //MAIN BREAKDOWN
    def viewBreakdown() {
        def h = [
            getChecks: {
                return  service.getRemittanceChecks([remittanceid: entity.objid]);
            },
            getCreditMemos: {
                return service.getRemittanceCreditMemos([remittanceid: entity.objid]);   
            }
        ]   
        return Inv.lookupOpener( "cashbreakdown", [entity:entity, handler: h ]);
    }
    
   
    def submit() {
        boolean pass = false;
        service.validateBreakdown([objid:entity.objid]);
        
        try {
            def h = { sig->
               pass = true;
               if( !entity.collector ) entity.collector = [:];
               entity.collector.signature = sig;
            }
            if ( com.rameses.rcp.sigid.SigIdDeviceManager.getProvider()?.test()) {
                def msg = "You are about to submit this remittance.Please ensure the entries are correct";
                Modal.show("verify_submit_with_signature", [handler: h, message: msg ]);
            }
        } catch( Throwable t ) {
            pass = MsgBox.confirm("You are about to post this transaction. Proceed?"); 
        } 
        
        if( pass ) { 
            def o = service.post([ 
                remittanceid: entity.objid, 
                collector: entity.collector, 
                cashbreakdown: entity.cashbreakdown                 
            ]);

            entity.txnno = o.txnno; 
            entity.state = o.state; 
            MsgBox.alert("Posting successful. Control No " + entity.txnno); 

            try { 
                return "_close"; 
            } finally {
                try {
                    if (caller) caller?.reload(); 
                } catch(Throwable t){;}
            }
        } 
        return null; 
    }

    def approve() {
        boolean pass = MsgBox.confirm("You are about to accept this remittance. Proceed?");
        if ( pass ) { 
            service.approve([ objid:entity.objid ]); 
            try { 
                return "_close"; 
            } finally {
                try {
                    if (caller) caller?.reload(); 
                } catch(Throwable t){;}
            }
        } 
        return null;
    }
    
    void disapprove() {
        boolean pass = MsgBox.confirm("You are about to reverse the approval of this remittance. Proceed?");
        if ( pass ) { 
            def res = service.disapprove([ objid:entity.objid ]);
            try {
                if (caller) caller?.reload(); 
            } catch(Throwable t){;}

            entity.state = res.state; 
            entity.liquidatingofficer = res.liquidatingofficer; 
        } 
    }     
} 