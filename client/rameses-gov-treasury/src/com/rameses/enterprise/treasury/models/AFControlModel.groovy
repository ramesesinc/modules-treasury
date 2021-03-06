package com.rameses.enterprise.treasury.models;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;

class AFControlModel extends CrudFormModel {

    @Service("AFControlService")
    def service;
    
    boolean has_admin_rights; 
        
    public boolean getShowAction() { 
        if( entity.state == 'ISSUED' ) {
            if ( entity.owner.objid == OsirisContext.env.USERID ) return true; 
            if ( entity.assignee.objid == OsirisContext.env.USERID ) return true; 
            return has_admin_rights; 
        }
        else if(entity.state.matches('OPEN|HOLD') ) {
            return true;
        }
        else {
            return false;
        }
    }
    
    void afterOpen() { 
        try {
            has_admin_rights = (Inv.lookup('af-control-admin-rights') ? true : false); 
        } catch(Throwable t) {
            has_admin_rights = false; 
        }
        _details = null;  
        if(binding) binding.refresh("detailInfo");
    }
    
    void activate() {
        if(entity.active==1)
            throw new Exception("Entry is already active");
        service.activateControl( entity );
        if(caller) caller.reloadEntity();
    }

    void deactivate() {
        if(entity.active==0)
            throw new Exception("Entry is already inactive");
        service.deactivateControl( entity );
        if(caller) caller.reloadEntity();
    }
    
     def assignFund() {
        if( entity.fund?.objid ) {
            throw new Exception("Fund is already assigned");
        }
        def h = { o->
            service.assignFund( [objid: entity.objid, fund: o ] );
            if(caller) caller.reloadEntity();
        };
        return InvokerUtil.lookupOpener('fund:lookup', [onselect: h]);
    }

    def unassignFund() {
        if( !entity.fund?.objid ) {
            throw new Exception("Fund is already unassigned");
        }
        service.unassignFund( [objid: entity.objid ] );
        if(caller) caller.reloadEntity();
    }

    def assignSubcollector() {
        def h = { o->
            service.assignSubcollector( [objid: entity.objid, assignee: o ] );
            if(caller) caller.reloadEntity();
        };
        return InvokerUtil.lookupOpener('subcollector:lookup', [onselect: h]);
    }

    def unassignSubcollector() {
        service.unassignSubcollector( [objid: entity.objid, owner: entity.owner ] );
        if(caller) caller.reloadEntity();
    }

    void changeMode( def mode ) {
        def v = service.changeMode( [objid: entity.objid, txnmode: mode ] );
        entity.txnmode = v.txnmode ;
        entity.active = v.active;
        if(caller) caller.reloadEntity();
    }    
    
    void changeModeOnline() {
        changeMode( "ONLINE" )
    }
    
    void changeModeOffline() {
        changeMode( "OFFLINE" )
    }
    
    void changeModeCapture() {
        changeMode( "CAPTURE" )
    }
    
    void changeRespCenter() {
        def h = { o->
            service.changeRespCenter( [objid:entity.objid, respcenter: o] );
            if(binding) binding.refresh("entity.respcenter.*")
            if(caller) caller.reloadEntity();
        }
        Modal.show("org:lookup", [onselect: h] );
    }
    
    void removeRespCenter() {
        service.removeRespCenter( [objid:entity.objid] );
        if(binding) binding.refresh("entity.respcenter.*")
        if(caller) caller.reloadEntity();
    }
    
    void cancelSeries() { 
        def m = [ entity: entity ]; 
        m.handler = {
            reloadEntity(); 
            if (caller) caller.reloadEntity(); 
        }
        Modal.show("af_control:cancelseries", m);
    }
    
    //second page
    public def getDetailInfo() { 
        return TemplateProvider.instance.getResult( "com/rameses/enterprise/treasury/views/AFControlPage.gtpl", [details: details] );
    }
    
    def _details;
    def getDetails() {
        if(_details !=null ) return _details;
        def m = [_schemaname:"af_control_detail"];
        m.findBy = [controlid: entity.objid];
        m.orderBy = "refdate,txndate";
        _details = queryService.getList(m);
        return _details;
    }
    
    
    def viewRef( def o ) {
        def op = Inv.lookupOpener( "aftxn:open", [entity: [objid: o.refid ]] );
        op.target = "popup";
        return op;
    }
    
    void hold() {
        def m = [_schemaname: 'af_control'];
        m.findBy = [objid: entity.objid];
        m.state = "HOLD";
        persistenceService.update( m );
        entity.state = "HOLD";
        if(caller) caller.reloadEntity();
    }
    
    void reopen() {
        def m = [_schemaname: 'af_control'];
        m.findBy = [objid: entity.objid];
        m.state = "OPEN";
        persistenceService.update( m );
        entity.state = "OPEN";
        if(caller) caller.reloadEntity();
    }
    
}    