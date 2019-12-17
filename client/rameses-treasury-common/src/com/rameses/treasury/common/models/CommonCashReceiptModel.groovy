package com.rameses.treasury.common.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.util.*;

/**
* This is extended by the ff:
*    VehicleCashReceiptModel
*    OboCashReceiptModel  
*/
public class CommonCashReceiptModel extends com.rameses.enterprise.treasury.models.AbstractCashReceipt {
    
    def prefix;
    def status;   
    def selectedItem;
    def txnid;
    
    //store something here in query that is constantly being sent to the server
    def query = [:];
    def billAmount = 0;
    def billItemList = [];
    def billingSvc;
    String _ruleName;
    boolean amountSpecified = false;
    boolean allowAdvancePayment = false;
    
    public String getConnection() {
        return invoker.module.properties.connection;
    }
    
    public String getCashReceiptServiceName() {
        return "BillingCashReceiptService";
    }
    
    public String getTitle() {
        if( invoker.properties.formTitle ) {
            return ExpressionResolver.getInstance().evalString(invoker.properties.formTitle,this);
        }
        if( invoker.caption ) {
            return invoker.caption;
        }
        return getContextName();
    }
    
    public String getContextName() {
        def pfn = invoker.properties.contextName;
        if(pfn) return pfn;
        pfn = workunit?.info?.workunit_properties?.contextName;
        if ( pfn ) return pfn; 
        return super.getSchemaName(); 
    }
    
    def _payOption = null;
    public String getPayOption() {
        if(_payOption == null ) {
            _payOption = invoker.properties.payOption;
            if(!_payOption ) _payOption = workunit?.info?.workunit_properties?.payOption;
        } 
        return _payOption; 
    }
    
    public String getRulename() {
        if(_ruleName ) return _ruleName;
        _ruleName = invoker.properties.rulename;
        if( _ruleName!=null ) {
            return _ruleName;
        }
        _ruleName = workunit?.info?.workunit_properties?.rulename;
        return _ruleName;
    }

    public String getDetails() {
        return "Details";
    }

    void afterLoadInfo() {;}
    boolean onNoItemsFound() { return false;}
    
    void lookupTxn() {
        def lookupName = getContextName() + ":cashreceipt_lookup"
        try {
            def h = { o->
                txnid = o.txnid;
                binding.refresh();
                return null;
            }
            def opener = Inv.lookupOpener(lookupName, [onselect: h ]);
            Modal.show( opener );
        }
        catch(ex) { 
            ex.printStackTrace(); 
            MsgBox.alert(lookupName + " not found");
        }
    }
    
    void findTxn() {
        if(txnid.contains(":")) txnid = txnid.split(":")[1];
        loadBill([id:txnid, action:'open']);
    }
    
    void loadBill( def p ) {
        p.collectiontype = entity.collectiontype;
        p.billdate = entity.receiptdate;
        
        //must replace the action so it will reflect the action passed;
        query.action = p.action;
        p.putAll( query );
        def pp = [ rulename: getRulename(), params: p ]; 
        def info = null;
        try {
            if( billingSvc==null) {
                billingSvc = InvokerProxy.instance.create(getCashReceiptServiceName(), null, getConnection() );
            }
            info = billingSvc.getBilling( pp );
        }
        catch(serverErr) {
            if ( p.action == "barcode" ) super.doClose(); 
            //log the errors starting from here 
            new RuntimeException( serverErr ).printStackTrace(); 
            //throw the actual error
            throw serverErr; 
        } 
        
        def warning = info?._warning; 
        if ( warning instanceof com.rameses.util.Warning ) {
            MsgBox.warn( warning.message ); 
        } 
        
        if ( !info.billitems ) { 
            if(allowAdvancePayment) {
                if ( !MsgBox.confirm('There are no bill items found. Do you want to pay in advance?')) 
                    throw new BreakException(); 
                    
                def amt = MsgBox.prompt("Enter amount for advance payment. ");
                if(!amt) throw new BreakException();
                pp.params.amtpaid = new BigDecimal(""+amt);
                info = billingSvc.getBilling( pp );
            }
            else {
                throw new Exception("No bill items found");
            }
        }
        
        entity.putAll( info ); 
        itemListModel.reload();
        updateBalances();
    }
    
    def getTotalAmount() {
        return NumberUtil.round( entity.items.sum{ it.amount } );  
    }   
    
    def showPayOptions() {
        if( amountSpecified ) 
            throw new Exception("Please reset amount specified first to Pay All");
        if( !getPayOption() ) return null;
        def m = [:];
        m.onselect = { o->
            loadBill( [id: txnid, action:'payoption', payoption: o ] );
            binding.refresh();
        }
        return Inv.lookupOpener( getPayOption(), m);
    }
    
    void specifyPayAmount() {
        def o = MsgBox.prompt("Enter Pay Amount");
        if(!o) return null;
        def p = [amtpaid: o, id:txnid, action:'open' ];
        loadBill( p );
        amountSpecified = true;
    }
    
    void payAll() {
        def p = [id:txnid, action:'open' ];
        loadBill( p );
        amountSpecified = false;
    }
    
    def resetPayOption() {
        loadBill( [id: txnid] );
    }
    
    public void validateBeforePost() {
        if( entity.balancedue !=  0 )
            throw new Exception("Amount must be equal to amount paid");
    }
    
    def itemListModel = [
        fetchList: { o->
            return entity.billitems;
        }
    ] as BasicListModel;
          
    public def previewReceipt() {
        return Inv.lookupOpener("cashreceipt_preview", [entity: entity] );
    }
    
    
}