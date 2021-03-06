package com.rameses.gov.treasury.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.reports.*;
import com.rameses.util.*;
        
class  CashTicketReceiptController {
            
    @Service("CashReceiptService")
    def service;
            
    @Binding
    def binding;
            
    String title = "Cash Ticket " ;
    def itemAmount = 0.0;
    def entity;
            
    def mode = "init"
    def infoHtml
           
    @PropertyChangeListener
    def listener = [
        "entity.amount": { o->
            if( ( (double)o % (double)entity.denomination)!=0) {
                MsgBox.err("Amount must be exact by denomination");
                entity.amount = 0.0
                binding.refresh("entity.amount")
                return false;
            }    
            entity.qtyissued = (int)  ((double)o / (double)entity.denomination);
            binding.refresh("entity.qtyissued");
        },
        "entity.qtyissued": { o->
            entity.amount = o * entity.denomination;
            binding.refresh("entity.amount");
        } 
    ]
            
    def itemListModel = [
        fetchList: { o->
            return entity.items;
        },
        onAddItem: {o-> 
            entity.items << o;
            itemAmount += o.amount;
        },
        onColumnUpdate: {o,name-> 
            if(entity.items) {
                itemAmount = entity.items.sum{ it.amount };
            }
        },
        onRemoveItem: { o->
            if(!MsgBox.confirm("You are about to remove this entry. Proceed?")) 
            return false;
            entity.items.remove( o );
            itemAmount = 0;
            if( entity.items ) {
                itemAmount = entity.items.sum{ it.amount };
            }
            return true;
        }
    ] as EditorListModel;
            

    def selectedItem;
    def getLookupItems() {
        def p = [:];
        p.put( "query.txntype", "cashreceipt");
        p.put( "query.collectorid" , entity.collector.objid );
        p.put( "query.collectiontype" , entity.collectiontype );
        if( entity.collectiontype.fund?.objid ) {
            p.fundid = entity.collectiontype.fund?.objid;
        }
        
        p.onselect = { o->
            selectedItem.item = o;
            selectedItem.amount = o.remove("amount");
            selectedItem.remarks = o.remove("remarks");
        } 
        return Inv.lookupOpener("cashreceiptitem:lookup", p );
    }
            
    def save() {
        if (!entity.items)
            throw new Exception("Please specify at least one item");
        if (itemAmount != entity.amount)
            throw new Exception("Total of items must be equal to amount collected");
        if ( entity.qtyissued > entity.qtybalance ) 
            throw new Exception('Qty Issued must be less than or equal to the Qty Balance'); 
                    
        if( MsgBox.confirm('You are about to post this transaction. Continue?')) { 
            if ( !entity.paidby ) entity.paidby = entity.collector?.name;
            if ( !entity.paidbyaddress ) entity.paidbyaddress = "-";
            
            if ( !entity.receiptno ) { 
                def key = "CT"+ com.rameses.util.Encoder.MD5.encode( entity.objid, "cashticket" ); 
                entity.receiptno = key; 
            }
                    
            entity = service.post( entity );
            mode = "completed"; 
            MsgBox.alert( "Receipt has been successfully saved.");
            infoHtml = TemplateProvider.instance.getResult( "com/rameses/enterprise/treasury/cashreceipt/cashreceipt.gtpl", [entity:entity] );
            binding.refresh("infoHtml")
            return "completed";
        }
    }
    
    void init() {
        
    }
} 
