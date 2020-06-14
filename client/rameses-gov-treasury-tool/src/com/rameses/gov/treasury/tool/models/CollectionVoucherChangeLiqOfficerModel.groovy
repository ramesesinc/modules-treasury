package com.rameses.gov.treasury.tool.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

class CollectionVoucherChangeLiqOfficerModel {

    @Service('CollectionVoucherModifyService') 
    def modifySvc; 
    
    def entity;
    def refreshHandler; 
    
    void init() { 
        def officer = null;
        def param = [:]; 
        param.onselect = { o-> 
            officer = o; 
            officer.title = o.jobtitle; 
            return null; 
        } 
        Modal.show('liquidatingofficer:lookup', param);
        if ( !officer ) return; 
        
        if ( officer?.objid ) { 
            modifySvc.changeLiqOfficer([ objid: entity.objid, liquidatingofficer: officer ]); 
            if ( refreshHandler ) {
                refreshHandler(); 
            }
        }
    }
    
}