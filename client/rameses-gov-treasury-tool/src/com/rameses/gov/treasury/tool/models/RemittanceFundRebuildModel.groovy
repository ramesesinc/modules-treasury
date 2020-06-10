package com.rameses.gov.treasury.tool.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

class RemittanceFundRebuildModel {

    @Service('RemittanceModifyService') 
    def modifySvc; 
    
    def entity;
    def refreshHandler; 
    
    void init() { 
        modifySvc.rebuildFund([ remittanceid: entity.objid ]);  
        MsgBox.alert('Rebuild successfully processed'); 
        if (refreshHandler) {
            refreshHandler(); 
        }
    }
    
}