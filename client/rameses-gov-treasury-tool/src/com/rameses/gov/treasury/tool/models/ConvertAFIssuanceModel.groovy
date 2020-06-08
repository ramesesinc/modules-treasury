package com.rameses.gov.treasury.tool.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.util.TemplateProvider;

class ConvertAFIssuanceModel {

    @Binding 
    def binding;
    
    @Service('PersistenceService')
    def persistenceSvc;
    
    @Service('AFIssuanceModifyService')
    def modifySvc; 
    
    def title = 'AF Issuance: Change Txn Type';
    
    def mode; 
    def afissue;
    
    void init() {
        mode = 'init'; 
    }
    
    def getLookupAFIssue() { 
        return Inv.lookupOpener('aftxn_issuance:lookup', [:]); 
    }
    
    def entity;
    
    def loadAFIssue() {
        def mm = [_schemaname: 'aftxn', objid: afissue.objid];
        def aftxn = persistenceSvc.read( mm ); 
        if ( aftxn.state.toUpperCase() != 'POSTED' ) 
            throw new Exception('Only POSTED transaction is allowed'); 
        if ( aftxn.txntype.toUpperCase() != 'ISSUE' ) 
            throw new Exception('Only ISSUE transaction type is allowed'); 
        
        entity = aftxn; 
        mode = 'edit';
        return mode;
    }
        
    def cancelEdit() {
        mode = 'init';
        return 'default';
    }
    
    def postUpdate() { 
        boolean b = MsgBox.confirm('You are about to post updates for this transaction. Proceed?'); 
        if ( !b ) return null; 
        
        def newitems = []; 
        newitems.addAll( entity.items.findAll{( it.oldtxntype != null && it.oldtxntype != it.txntype )} ); 

        def newcostitems = []; 
        entity.items.each{ o-> 
            def subitems = o.items.findAll{( it.oldsalecost != null && it.oldsalecost != it.salecost )} 
            subitems.each{ it.aftxnitemid = o.objid; }
            newcostitems.addAll( subitems );  
        }

        if ( !newitems && !newcostitems ) 
            throw new Exception('There are no changes made for this transaction'); 

        newitems = newitems.collect{[ objid: it.objid, txntype: it.txntype ]}         
        modifySvc.changeTxnType([ 
            objid: entity.objid, items: newitems, costitems: newcostitems 
        ]); 
        MsgBox.alert("Transaction successfully processed"); 
        
        afissue.clear(); 
        afissue = null;
        
        entity.clear(); 
        entity = null; 
        
        init(); 
        return 'default'; 
    }
    
    
    def getInfo() { 
        def param = [ entity: entity ]; 
        return TemplateProvider.instance.getResult( "com/rameses/gov/treasury/tool/views/ConvertAFIssuanceInfo.gtpl", param );
    }
    
    void changeTxnType( param ) {
        def item = entity.items.find{ it.objid == param.aftxnitemid } 
        if ( !item ) return;
        
        def pp = [fields:[]];
        pp.data = [txntype: item.txntype]; 
        pp.fields << [type:'combo', name:'txntype', caption:'Txn Type', required: true, itemsObject: ['COLLECTION','SALE']];
        
        def newtxntype = null; 
        pp.handler = { o->
            newtxntype = o.txntype; 
        }
        pp.formTitle = "<html><br><b>Change Settings</b><br><br></html>";
        Modal.show("dynamic:form", pp, [title:'Change Txn Type', height:250]); 
        
        if ( newtxntype ) {
            if ( !item.oldtxntype ) { 
                item.oldtxntype = item.txntype; 
            }
            
            item.txntype = newtxntype; 
            if ( item.txntype == 'COLLECTION') {
                item.salecost = 0.0; 
            }
        }
        
        binding.refresh(); 
    } 
    
    void changeSaleCost( param ) {
        def item = entity.items.find{( it.objid == param.aftxnitemid )} 
        if ( !item ) return; 
        
        item = item.items.find{ it.uuid == param.uuid } 
        if ( !item ) return;
        
        item.cost = (item.cost ? item.cost : 0.0);
        item.salecost = (item.salecost ? item.salecost : 0.0); 
        
        def pp = [fields:[]];
        pp.data = [salecost: item.salecost, cost: item.cost]; 
        pp.fields << [type:'label', name:'cost', caption:'Cost/Stub', numberFormat:"#,##0.00", preferredSize:"100, 20"];
        pp.fields << [type:'decimal', name:'salecost', caption:'Sale Cost', required: true, preferredSize:"100, 20" ];
        
        def newsalecost = null; 
        pp.handler = { o->
            newsalecost = o.salecost; 
        }
        pp.formTitle = "<html><br><b>Change Sale Cost</b><br><br></html>";
        Modal.show("dynamic:form", pp, [title:'Change Settings', width:300, height:250]); 
        
        if ( newsalecost ) {
            if ( !item.oldsalecost ) {
                item.oldsalecost = item.salecost; 
            }
            item.salecost = newsalecost; 
        }
        
        binding.refresh(); 
    }
}