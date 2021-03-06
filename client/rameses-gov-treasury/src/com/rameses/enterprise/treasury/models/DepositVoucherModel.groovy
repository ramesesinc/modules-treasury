package com.rameses.enterprise.treasury.models;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.seti2.models.*;


class DepositVoucherModel extends CrudFormModel {

    @Service("DepositVoucherService")
    def depositSvc;   
    
    def selectedFund;
    def selectedCheck;
    def checkListModel;
    
    void addExternalCheck() {
        def h = { o, saveType ->
            checkListModel.reload();
        }
        def d = [
            depositvoucherid: entity.objid,
            state: 'FOR-DEPOSIT',
            external : 1,
        ];
        Modal.show("checkpayment:create", [handler: h, external:true, defaultData:d  ]);
    }
    
    void removeExternalCheck() {
        if(!selectedCheck ) throw new Exception("Please select a check");
        def m = [:];
        m._schemaname = "checkpayment";
        m.objid = selectedCheck.objid;
        persistenceService.removeEntity( m );
        checkListModel.reload();
    }
    
    public def post() {
        if(! MsgBox.confirm("You are about to post this voucher. Continue?")) return;
        depositSvc.post( [objid: entity.objid ] );
        MsgBox.alert("Posting successful");
        return "_close";
    }
    
    def viewFund() {
        if(!selectedFund) return null;
        return Inv.lookupOpener("depositvoucher_fund:open", [ entity: [objid:selectedFund.objid] ] );
    }
    
    boolean isViewReportAllowed() { 
        return false; 
    }  
    
    def popupReports( inv ) {
        def popupMenu = new PopupMenuOpener();
        def list = Inv.lookupOpeners( inv.properties.category, [entity:entity] );
        list.each{
            popupMenu.add( it );
        }
        return popupMenu;
    }     
}    