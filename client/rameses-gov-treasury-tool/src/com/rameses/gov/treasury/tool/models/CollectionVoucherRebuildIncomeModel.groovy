package com.rameses.gov.treasury.tool.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

class CollectionVoucherRebuildIncomeModel {

    @Service('IncomeSummaryModifyService') 
    def modifySvc; 
    
    def entity;
    def refreshHandler; 
    
    void init() { 
        def param = [:]; 
        param.formTitle = 'Rebuild Income Summary'; 
        param.initHandler = { asyncHandler-> 
            modifySvc.rebuildByLiquidation([ objid: entity.objid ], asyncHandler);
        }
        Modal.show('rebuild_proc:open', param);  
    }    
}