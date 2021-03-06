package com.rameses.enterprise.treasury.models;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;


/******************************************************************************
* The lookup criteria is as follows:
*    a) NO client code
*           Display all itemaccount in collectiongroup  
*    c) There is client code, no account in collection type
*           Display only itemaccount where orgid = clientcode
*    d) There is client code, there is account in collection type         
*           Display items in itemaccount for collectiontype
*           where orgid is NULL or orgid = clientcode
*******************************************************************************/
class CollectionGroupLookupModel extends CrudLookupModel {
    
    //pass the entity so the collection group lookup can insert the items on it;
    //this entity could be 
    def receipt;

    void afterInit() {
        //if ( receipt == null ) throw new Exception("receipt must be set in collectiongroup lookup");
    }
    
    public def getCustomFilter() {
        def orgid = null;
        if( query.customorgid ) {
            orgid = query.customorgid;
        }
        else if( OsirisContext.env.ORGROOT != 1 ) {
            orgid =  OsirisContext.env.ORGID;
        }
        if(orgid == null ) {
            return ["orgid IS NULL"]
        }
        else {
            return ["orgid = :orgid", [orgid: orgid]];
        }
    }
    
    //if you will use collection group lookup in rules make 
    def lookupSelectedValue( def o ) {
        if(receipt == null) return o;
        if(tag == "rules") return o;
        
        //lookup items here
        def orgid = null;
        if( query.customorgid ) {
            orgid = query.customorgid;
        }
        else if( OsirisContext.env.ORGROOT != 1 ) {
            orgid =  OsirisContext.env.ORGID;
        }
        
        def m = [_schemaname:"vw_cashreceipt_itemaccount_collectiongroup"];
        m.findBy = [collectiongroupid: o.objid ];
        if( orgid ) {
            m.where = ["(orgid IS NULL OR orgid = :orgid)", [orgid: orgid]];
        }
        def items = queryService.getList(m);
        if (!items) {
            fireRules( o ); 
            return o;
        }
            
        items.sort{( it.sortorder ? it.sortorder : 0 )} 

        boolean has_sharing = ( o.sharing.toString() == "1"); 
        if ( has_sharing ) { 
            def amt = MsgBox.prompt( "Please enter amount" );
            if( amt == null ) return null;

            def sharing_amount = new BigDecimal( amt.toString() );
            items.each {
                it.amount = NumberUtil.round( sharing_amount * (it.defaultvalue ? it.defaultvalue : 0.0));
            }           
        }
        else { 
            def fxunits = items.findAll{( it.valuetype == 'FIXEDUNIT' )}
            if ( fxunits ) {
                def sqty = MsgBox.prompt("Enter qty");
                if( !sqty || sqty == "null" ) throw new Exception("Please provide qty"); 
                if( !sqty.isInteger() ) throw new Exception("Qty must be numeric"); 
                
                def nqty = Integer.parseInt( sqty ); 
                if ( nqty <= 0 ) throw new Exception("Qty must be greater than zero"); 

                fxunits.each{ fxu-> 
                    fxu.amount = (fxu.defaultvalue ? fxu.defaultvalue : 0.0) * nqty; 
                    fxu.remarks = "qty@"+ nqty;
                } 
            }
        }

        def newitems = []; 
        items.each{ 
            def rci = [objid: 'RCTI-'+ new java.rmi.server.UID()]; 
            if ( it.amount != null ) { 
                rci.amount = it.amount; 
            } else {
                rci.amount = ( it.defaultvalue ? it.defaultvalue : 0.0 );
            }
            rci.item = [ 
                objid : it.objid, 
                code  : it.code, 
                title : it.title, 
                fund  : it.fund, 
                valuetype : it.valuetype, 
                defaultvalue : it.defaultvalue 
            ];          

            if ( it.remarks ) rci.remarks = it.remarks; 
            else if ( it.valuetype == 'FIXEDUNIT' ) rci.remarks = "qty@1"; 

            newitems << rci; 
        }
        receipt.items.addAll( newitems ); 
        receipt.amount = receipt.items.sum{( it.amount ? it.amount : 0.0 )} 
    }    
    
    void fireRules(def collectiongroup) {
        def params = [billdate: receipt.receiptdate];
        params.collectiongroup = collectiongroup;
        def h1 = { result->
            if( result  == "_close" ) {
                return null;
            }
            else if( !result?.items ) {
                MsgBox.err( new Exception("No items defined"));
            }
            else {
                receipt.items.addAll( result.items );                
            }
        }
        def op = Inv.lookupOpener("collection_rule", [rulename:"collection", params: params, handler: h1 ] );
        Modal.show( op );
    }
}    