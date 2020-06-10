package com.rameses.gov.treasury.tool.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

class RemittanceModifyCashBreakdownModel {

    @Service('RemittanceModifyService') 
    def modifySvc; 
    
    def entity;
    def refreshHandler; 
    
    void init() { 
        def result = null; 
        def p = [ total: entity.totalcash, cashbreakdown: entity.cashbreakdown ];
        p.handler = { o-> 
            def newtotalcash = o.cashbreakdown.sum{( it.amount ? it.amount : 0.0 )} 
            if ( newtotalcash == null ) newtotalcash = 0.0; 
            if ( newtotalcash != entity.totalcash ) 
                throw new Exception('Your total cash breakdown must be equal to the total cash'); 

            result = [ cashbreakdown: o.cashbreakdown ]; 
        }
        Modal.show("cashbreakdown", p );        
        if ( !result ) return;
                
        result.objid = entity.objid; 
        modifySvc.updateCashBreakdown( result ); 
        MsgBox.alert('Cash Breakdown successfully updated'); 
        if (refreshHandler) {
            refreshHandler(); 
        }
    }
    
}