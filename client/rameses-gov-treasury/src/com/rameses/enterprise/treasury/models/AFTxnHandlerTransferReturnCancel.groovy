package com.rameses.enterprise.treasury.models;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.reports.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;
import com.rameses.rcp.framework.ValidatorException;


class AFTxnHandlerTransferReturnCancel extends AFTxnHandler {

    def afType;
    def afTypes;
    
    def txnType;
    def txnTypes;
    
    @PropertyChangeListener
    def listener = [
        "entity.issuefrom": { o-> 
            if( binding && afType ) {
                afListModel.reload();
            }
        }, 
        "afType" : { o->
            if( binding ) {
                afListModel.reload();
            }
        }
    ];
    
    def init() {
        def m = [_schemaname: 'af'];
        m.where = ["1=1"];
        m.orderBy = "objid";
        afTypes = queryService.getList(m);
        
        if ( entity.txntype == 'MANUAL_ISSUE' ) {
            m.clear();
            m._schemaname = 'aftxn_type'; 
            m.where = [" poststate = 'OPEN' "];
            m.select = 'formtype'; 
            m.orderBy = 'formtype'; 
            this.txnTypes = queryService.getList( m ).collect{ it.formtype } 
            if ( this.txnTypes ) this.txnTypes.unique(); 
        }
        return super.init();
    }
    
    void setTxnType( newValue ) {
        this.txnType = newValue; 
        if( binding ) {
            afListModel.reload();
        }
    }
    
    def afListModel = [
        isMultiSelect: {
            return true;
        },
        fetchList: { o->
            if(!afType) return [];
            //if not manual issue it must require issuefrom
            if(entity.txntype!='MANUAL_ISSUE' && !entity.issuefrom?.objid ) return [];
            
            def list = [];
            def p = [:];
            def m = [_schemaname:"af_control"];
            
            if( afType ) {
                list << "afid = :afid";
                p.afid = afType.objid;
            }
            
            if( entity.txntype == "MANUAL_ISSUE") {
                list << "state = 'OPEN' ";
                
                if ( txnType ) { 
                    p.txntype = txnType; 
                    list << "currentdetail.reftype = :txntype "; 
                }
            }
            else {
                list << "owner.objid = :ownerid";
                list << "owner.objid = assignee.objid";
                list << "currentseries <= endseries";
                list << "NOT(txnmode = 'REMOTE')";
                p.ownerid = entity.issuefrom?.objid;
            }
            
            list << "active = 0";
            m.where = [ list.join(" AND "), p ];
            m.orderBy = "dtfiled,batchno,stubno,startseries";
            m._limit = 100; 
            
            //m.debug = true;
            return queryService.getList( m ); 
        }
    ] as BasicListModel;

    def save() {
        if ( (entity.txntype == "TRANSFER") && (entity.issuefrom.objid == entity.issueto.objid) ) 
                throw new Exception("Issued To must not be the same with the Issued From. Please select another");
        
        if(!MsgBox.confirm("You are about to save this record. Proceed?")) return null;
        entity._schemaname = "aftxn";

        entity.afitems = afListModel.selectedValue; 
        entity.afitems.each{
            it.remove('currentdetail'); 

            def m = [:]; 
            m.item = it.afunit; 
            m.item.putAll( it.af ); 
            m.unit = it.unit;
            m.txntype = entity.txntype; 
            m.qtyserved = m.qty = 1; 
            m.linetotal = m.cost = it.cost; 
            entity.items << m; 
        }
        def e = persistenceService.create(entity);
        entity.clear();
        entity.putAll(e);
        return forward();
    }    
}    