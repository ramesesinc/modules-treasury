package com.rameses.enterprise.treasury.models;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;

class AFIssueNewModel extends CrudPageFlowModel {

    @Service("AFIssueService")
    def svc;
    
    boolean withrequest = false;
    def afrequest;
    
    def txnTypes;
    def reqtype = null;
    def selectedItem;
    
    
    void create() {
        entity = [:];
        entity.items = [];
        afrequest = null;
        itemListHandler.reload();
    }
    
    void initNew() {
        if(afrequest!=null) {
            def req = persistenceService.read( [_schemaname:'afrequest', objid: afrequest.objid ] );
            entity.request = [objid:req.objid , reqno: req.reqno];
            entity.issueto = req.requester;
            req.items.each {
                def m = [:];
                m.item = it.item;
                m.unit = it.unit;
                m.qtyrequested = it.qty;
                m.qtyissued = 0;  
                m.txntype = 'COLLECTION';
                entity.items << m;
            }
            txnTypes = ["COLLECTION"];
        }
        else {
            txnTypes = ["COLLECTION", "SALE"]
        }
    }

    //the only lookup here is for the request because the other request os for purchase (af receipt)
    public def getLookupRequest() {
        return Inv.lookupOpener( "afrequest_collection:lookup", [:] );
    }
    
    public def getUnitList() {
        return selectedItem.item.units*.unit;
    }
    
    def itemListHandler = [
        fetchList : { o->
            return entity.items;
        },
        onAddItem: { o-> 
            if ( !o.qtyrequested ) o.qtyrequested = o.qtyissued;
            
            entity.items << o;
        },
        onRemoveItem: { o->
            entity.items.remove(o);
        },
        onColumnUpdate: { o,colName->
            if ( colName == "item" ) {
                o.item.objid = o.item.itemid;
                o.unit = o.item.unit; 
                o.saleprice = o.item.saleprice; 
                computeCost( o ); 
            }
            if(colName=="qtyissued") { 
                if ( afrequest && o.qtyissued > o.qtyrequested ) 
                    throw new Exception("Qty to issue must be less than qty requested");

                computeCost( o ); 
            } 
        }
    ] as EditorListModel;
    
    private void computeCost( o ) {
        o.cost = (o.qtyissued ? o.qtyissued : 0) * (o.saleprice ? o.saleprice : 0.0); 
    }
    
    public def getInfo() {
        return TemplateProvider.instance.getResult( "com/rameses/enterprise/treasury/templates/AFIssueDetail.gtpl", [entity:entity] );
    }
    
    
    void loadOpenAF() {
        if ( afrequest != null ) {
            def list = entity.items.findAll{ it.qtyrequested!=null && it.qtyrequested > it.qtyissued };
            if ( list ) throw new Exception("Qty Issued must be less than or equal to the qty requested")
        }
        
        entity = svc.fetchOpenAF(entity);
    }
    
    void post() { 
        svc.post( entity ); 
    }
}    