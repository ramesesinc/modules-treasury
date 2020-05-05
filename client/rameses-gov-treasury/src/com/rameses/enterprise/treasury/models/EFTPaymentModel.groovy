package com.rameses.enterprise.treasury.models;

import com.rameses.rcp.annotations.*;
import com.rameses.seti2.models.CrudFormModel;

class EFTPaymentModel extends CrudFormModel  { 

    def viewReceipt() {
        return Inv.lookupOpener( "cashreceipt:open", [entity: [objid: entity.receiptid ]]);
    }
    
    boolean isViewReportAllowed() { 
        return false; 
    }    
} 
