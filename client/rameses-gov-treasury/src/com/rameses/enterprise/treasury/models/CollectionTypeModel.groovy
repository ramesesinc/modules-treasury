package com.rameses.enterprise.treasury.models;

import com.rameses.osiris2.common.*;
import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.seti2.models.*;
        
public class CollectionTypeModel extends CrudFormModel {

   
    def formTypes;
    def categoryList;
    def handlers;
    def batchHandlers;
    def selectedAccount;
    def orgListHandler;
    
    def handlerTypes;
    
    void afterCreate() { 
        entity.state = 'ACTIVE';
        entity.sortorder = 0; 
        entity.allowonline = 1;
        entity.allowoffline = 0;
        entity.allowbatch = 0;
        if ( entity.info == null ) {
            entity.info = [:]; 
        }
    } 

    void afterOpen() {
        if(!entity.allowonline) entity.allowonline = 0;
        if(!entity.allowoffline) entity.allowoffline = 0;
        if(!entity.allowbatch) entity.allowbatch = 0;
        if( entity.info == null ) entity.info = [:]; 
    }
    
    void afterInit() {
        def m = [_schemaname:'af']
        m.select = "objid";
        m._limit = 1000;
        formTypes = queryService.getList(m)*.objid;
        
        handlerTypes = []; 
        Inv.lookup("collectiontype:handler").groupBy{ it.properties.name }.each{ k,v-> 
            def hmap = [:]; 
            hmap.putAll( v.first().properties ); 
            
            try {
                hmap.index = Integer.parseInt( hmap.index.toString()); 
            } catch(Throwable t) {
                hmap.index = 0;
            }
            handlerTypes << hmap; 
        }
        
        handlers = handlerTypes.collect{ it.name }.unique();
        handlers.sort{ it }
        
        batchHandlers = Inv.lookup( "collectiontype:batchhandler" )*.properties.name;
        batchHandlers.sort{ it }
        batchHandlers.unique(); 
    }
    
    boolean isMultireceipt() {
        def o = handlerTypes.find{ it.name == entity.handler }
        if ( o == null ) return false; 
        return (o.multireceipt.toString() == 'true');
    }
    
    boolean isAllowMultiReceiptOption() {
        if ( mode.toString().matches('create|edit|update')) {
            return isMultireceipt(); 
        }
        return false; 
    }
    
    def categoryModel = [
        fetchList: { o->
            return categoryList;
        },
        onselect: { o->
            entity.category = o.category;
        }
    ] as SuggestModel;
    
    void addOrg() {
        def h = { arr-> 
            try { 
                arr.each {o-> 
                    def item = [ _schemaname: "collectiontype_org" ];
                    item.objid = entity.objid + ":" + o.objid;
                    item.collectiontypeid = entity.objid;
                    item.org = o;
                    item.org.type = o.orgclass;
                    persistenceService.save( item );
                }
            } finally {  
                orgListHandler.reload(); 
            }  
        } 
        Modal.show( "org:lookup", [onselect: h, multiSelect: true] );
    }
    
    def getQuery() {
        return [objid: entity.objid];
    }
}