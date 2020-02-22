package com.rameses.gov.treasury.cashreceipt.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.enterprise.treasury.cashreceipt.BasicCashReceipt;

class MiscMultiCashReceiptModel extends BasicCashReceipt {

    @Service('MiscMultiCashReceiptService')
    def multiReceiptSvc;
    
    String entityName = "misc_multi_cashreceipt"

    def post() {
        if( entity.amount <= 0 ) 
            throw new Exception("Please select at least an item to pay");
        if( entity.totalcash + entity.totalnoncash == 0 )
            throw new Exception("Please make a payment either cash or check");
            
        def numformat = new java.text.DecimalFormat('0.00'); 
        entity.totalcash = new BigDecimal( numformat.format( entity.totalcash )); 
        entity.cashchange = new BigDecimal( numformat.format( entity.cashchange )); 
        entity.totalnoncash = new BigDecimal( numformat.format( entity.totalnoncash )); 
        entity.amount = new BigDecimal( numformat.format( entity.amount )); 
        if( (entity.totalcash-entity.cashchange) + entity.totalnoncash != entity.amount )
            throw new Exception("Total cash and total non cash must equal the amount");    
            
        if(entity.balancedue > 0)
            throw new Exception("Please ensure that there is no balance unpaid");

        validateBeforePost();
        
        int itemsperpage = -1; 
        try {
            def vars = multiReceiptSvc.getSysVars([ formno: entity.formno ]);
            def varname = 'af'+ entity.formno +'_itemsperpage'; 
            itemsperpage = Integer.parseInt( vars.get( varname ).toString()); 
        } catch(Throwable t){} 
        
        if ( itemsperpage <= 0 ) {
            itemsperpage = 1000; 
        }
        
        if ( entity.items.size() <= itemsperpage ) {
            return super.post(); 
        } 
        else {
            entity.itemsperpage = itemsperpage;
            return postReceipts(); 
        } 
    } 
    
    void postReceipts() {
        if ( entity.checks && entity.checks.size() > 1 ) {
            throw new Exception("Multiple checks is not supported at this time"); 
        }

        int itemsperpage = entity.itemsperpage;
        
        def receipts = [];
        entity.items.eachWithIndex{ o,idx-> 
            int idxno = idx+1;
            int idxpg = idxno / itemsperpage; 
            if ((idxno % itemsperpage) > 0) idxpg++; 
            
            def rct = null; 
            if ( receipts.size() < idxpg ) {
                rct = [:]; 
                entity.each{ k,v-> 
                    if ( v instanceof Collection ) {
                        //do not copy 
                    } else if ( v instanceof Map ) {
                        //do not copy 
                    } else {
                        rct.put(k, v); 
                    }
                }
                rct.collectiontype = entity.collectiontype; 
                rct.collector = entity.collector;
                rct.payer = entity.payer;
                rct.user = entity.user; 
                rct.org = entity.org; 
                rct.paymentitems = [];
                rct.shares = [];
                rct.items = [];
                receipts << rct; 
            } 
            else { 
                rct = receipts[idxpg-1];
            } 
            
            rct.items << o; 
        }
        
        def amount = entity.amount;
        def totalcash = entity.totalcash;
        def totalnoncash = entity.totalnoncash;
        if ( totalcash > 0 && totalnoncash > 0 ) { 
            throw new Exception("Please select only 1 method of payment"); 
        }
        
        def series = entity.series; 
        def endseries = entity.endseries; 
        def qtybalance = (endseries - series) + 1; 
        if ((qtybalance - receipts.size()) < 0) {
            def msg = "This transaction requires "+ receipts.size() + 
                      " receipts but the remaining qty balance of your AF is "+ qtybalance; 
            throw new Exception( msg ); 
        }
                
        boolean paidInCash = (entity.paymentitems ? false : true); 
        
        def serieslength = entity.serieslength; 
        def prefix = (entity.prefix ? entity.prefix : '');
        def suffix = (entity.suffix ? entity.suffix : '');
        receipts.each{ 
            it.series = series;
            
            def skey = it.controlid +'-'+ it.series.toString();
            it.objid = 'RCT-'+ com.rameses.util.Encoder.MD5.encode( skey );
            it.receiptno = prefix + it.series.toString().padLeft(serieslength,'0') + suffix; 
            it.amount = it.items.sum{ it.amount }
            it.cashchange = 0.0; 
            if ( paidInCash ) {
                it.totalcash = it.amount; 
                it.totalnoncash = 0.0; 
            }
            else {
                it.totalcash = 0.0; 
                it.totalnoncash = it.amount; 
                it.checks = []; 
                
                def paymentitems = []; 
                def chk = (entity.checks ? entity.checks.first() : null); 
                if ( !chk ) {
                    def checks = entity.paymentitems.collect{ it.check }
                    chk = (checks ? checks.first() : [:]); 
                }
                
                if ( chk ) {
                    chk.split = 1; 
                    
                    it.items.groupBy{ oo-> oo.item.fund }.each{ k,v-> 
                        def pmt = [:];
                        pmt.check = chk; 
                        pmt.reftype = 'CHECK';
                        pmt.refid = chk.objid; 
                        pmt.refno = chk.refno;
                        pmt.refdate = chk.refdate;
                        pmt.amount = v.sum{ it.amount }
                        pmt.fund = k;
                        pmt.particulars = pmt.refno +' ('+ chk.bank.name +') dated '+ pmt.refdate; 
                        paymentitems << pmt; 
                    }
                }
                it.paymentitems = paymentitems;
            }
            series++; 
        }
        
        def receiptnos = receipts.collect{ it.receiptno }.join(", "); 
                
        boolean pass = false;
        def h = { pass = true; }
        Modal.show("cashreceipt_confirm", [ handler:h, receiptno: receiptnos ]);
        if ( !pass ) return null; 
        
        boolean postok = false;
        try { 
            beforePost();
            entity._paymentorderid = _paymentorderid; 
            def res = multiReceiptSvc.post( entity, receipts ); 
            
            entity.receipts = (res ? res : []); 
            
            postok = true; 
        } 
        catch(e) { 
            e.printStackTrace(); 
            postError(); 
            throw e; 
        }

        if ( postok ) {
            completed = true; 
            binding.fireNavigation('completed'); 
            binding.refresh(); 
        } 
        
        try {
            def fo = ( entity.receipts ? entity.receipts.first() : null ); 
            entity._options = (fo?._options == null ? [:] : fo._options);

            if(entity.txnmode.equalsIgnoreCase("ONLINE") && mainProcessHandler==null) { 
                print();
            } 
        }
        catch(e) {
            e.printStackTrace();
            MsgBox.alert("warning! no form handler found for.  " + entity.formno +". Printout is not handled" );
        }

        if( mainProcessHandler ) { 
            mainProcessHandler.forward( entity );
        }
        return null;         
    }
    
    void print() {
        // printing of receipts is handled by the mainProcessHandler
        // when forward method is called 
    } 
}
