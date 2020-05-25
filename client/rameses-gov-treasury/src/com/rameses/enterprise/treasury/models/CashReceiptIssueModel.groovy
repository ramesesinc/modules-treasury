package com.rameses.enterprise.treasury.models;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.reports.*;
import com.rameses.util.*;
import com.rameses.enterprise.treasury.util.CashReceiptPrintUtil;

class CashReceiptIssueModel extends CashReceiptAbstractIssueModel  {

    @Service('Var')
    def varSvc;

    def handler;
    boolean showPrintDialog = false;
    
    def printPreviewOpener;

    //default to af51
    public String getDefaultAfType() {
        return "51";
    }
    
    public String getFormType() {
        return "serial";
    }

    def processHandler = [
        back : {
            def op = super.signal("back");
            binding.fireNavigation( op );
        },
        forward: { o-> 
            if ( o ) entity.putAll( o ); 
                        
            try { 
                def op = super.signal("forward");
                binding.fireNavigation( op ); 
            }
            finally {
                if ( mode == "ONLINE" ) { 
                    if ( o.hasCustomPrinting ) { 
                        // it has a custom printing facility, so will do nothing 
                    } 
                    else { 
                        printReceipt();
                    }
                }
            }
        }
    ];
    
    void launchNew() {
        createNew();
        
        def m = [entity: entity, afcontrol: afcontrol, mainProcessHandler: processHandler];
        handler = Inv.lookupOpener("cashreceipt:"+ collectionType.handler, m); 
    }
    
    void cleanUp() {
        entity = [:];
        handler = null;
    }

    
    def getInfoHtml() {
        return TemplateProvider.instance.getResult( "com/rameses/enterprise/treasury/cashreceipt/cashreceipt.gtpl", [entity:entity] );
    }
    
    def loadBarcode() {
        def h = { o->
            mode = "ONLINE";
            collectionType = o.collectiontype;
            afType = o.collectiontype.formno;
            
            //loaded collection type
            collectionTypes.mode = "ONLINE";
            collectionTypes.afType = afType; 
            
            //initialize the entity. If there is _paymentorderid 
            //it means there is a related payment order for this.
            createNew();
            def op = null;
            if ( o._paymentorderid ) {
                entity.payer = o.info.payer;
                entity.paidby = o.info.paidby;
                entity.paidbyaddress = o.info.paidbyaddress;
                entity.amount = o.info.amount;
                entity.items = o.info.items.collect{ [item: it.item, amount:it.amount, remarks:it.remarks ] };
                entity.remarks = o.info.particulars;
                def pp = [:];
                pp.entity = entity;
                pp.mainProcessHandler = processHandler;
                pp._paymentorderid = o._paymentorderid;
                handler = Inv.lookupOpener("cashreceipt:"+ collectionType.handler, pp);
            }
            else {
                def pp = [:];
                pp.info = o.info;
                pp.entity = entity;
                pp.barcodeid = o.barcodeid;
                pp.mainProcessHandler = processHandler;
                handler = null;
                try {
                    handler = Inv.lookupOpener("cashreceipt:barcode:"+ o.prefix, pp );
                }catch(ee){;}
                if(handler==null) {
                    //we should not call loadBarcode bec. it is already handled during start;
                    def handlerName = entity.collectiontype.handler;
                    handler = Inv.lookupOpener("cashreceipt:"+ handlerName, pp);
                }
            }
            def np = super.signal("movenext");
            binding.fireNavigation( np );
        }
        return Inv.lookupOpener( "cashreceipt_barcode", [handler: h] );
    }
    
    public void printReceipt() {
        if ( afcontrol?.afunit?.cashreceiptprintout ) { 
            def u = new CashReceiptPrintUtil( binding: binding ); 
            u.showPrinterDialog = ( entity._options?.canShowPrinterDialog.toString() == 'false' ? false : true ); 
            u.print( afcontrol.afunit.cashreceiptprintout, entity );
        } 
        else {
            MsgBox.alert("Unable to print receipt. Please define a setting for cashreceipt printout in AF Unit"); 
        }
    }
    
    public void reprintReceipt() {
        if ( afcontrol?.afunit?.cashreceiptprintout ) { 
            def u = new CashReceiptPrintUtil( binding: binding ); 
            u.showPrinterDialog = ( entity._options?.canShowPrinterDialog.toString() == 'false' ? false : true );             
            u.reprint( afcontrol.afunit.cashreceiptprintout, entity );
        } 
        else {
            MsgBox.alert("Unable to print receipt. Please define a setting for cashreceipt printout in AF Unit"); 
        }
    } 
    
    public void printReceiptDetail() {
        if ( afcontrol?.afunit?.cashreceiptdetailprintout ) {
            def u = new CashReceiptPrintUtil( binding: binding ); 
            u.printDetails( afcontrol.afunit.cashreceiptdetailprintout, entity );
        } 
        else {
            MsgBox.alert("Unable to print receipt detail. Please define a setting for cashreceipt detail printout in AF Unit"); 
        }
    }
        
    def void_requires_approval; 
    boolean isVoidRequiresApproval() {
        if ( void_requires_approval == null ) {
            def sval = varSvc.get('cashreceipt_void_requires_approval'); 
            void_requires_approval = ( "true".equals(sval.toString()) ? true : false); 
        }
        if ( void_requires_approval instanceof Boolean ) {
            return (void_requires_approval ? true : false); 
        }
        return false; 
    }

    public void voidReceipt() { 
        if ( entity.voided.toString().matches('1|true')) {
            MsgBox.alert('Cash Receipt is already voided'); 
        } else {
            Modal.show( "void_cashreceipt", [applySecurity : isVoidRequiresApproval(), receipt: entity ]);
        }
    }

    public viewCollectionSummary() {
        Modal.show( "cashreceipt_collection_summary:view", [:] );
    }    
}    