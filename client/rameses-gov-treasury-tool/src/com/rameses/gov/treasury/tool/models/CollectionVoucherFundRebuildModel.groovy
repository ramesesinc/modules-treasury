package com.rameses.gov.treasury.tool.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

class CollectionVoucherFundRebuildModel {

    @Service('CollectionVoucherModifyService') 
    def modifySvc; 
    
    def entity;
    def refreshHandler; 
    
    void init() { 
        def param = [:]; 
        param.formTitle = 'Rebuild Fund Summary'; 
        param.initHandler = { asyncHandler-> 
            modifySvc.rebuildFund([ objid: entity.objid ], asyncHandler);
        }
        param.doneHandler = {
            if (refreshHandler) {
                refreshHandler(); 
            }
        }
        Modal.show('rebuild_proc:open', param);  
    }    
}