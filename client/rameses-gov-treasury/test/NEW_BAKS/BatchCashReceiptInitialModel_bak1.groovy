package com.rameses.enterprise.treasury.models;

import com.rameses.osiris2.common.*;
import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.util.*;
import java.text.*;

class BatchCashReceiptInitialModel  {

    @Binding
    def binding;

    def clientContext = com.rameses.rcp.framework.ClientContext.currentContext;
    def session = OsirisContext.getSession();

    @Service("CashReceiptService")
    def cashReceiptSvc;

    @Service("CollectionTypeService")
    def collectionTypeSvc;

    @Service("OrgService")
    def orgSvc;

    @Service("OrgLookupService")
    def orgLookupSvc;

    @Service("DateService")
    def dtsvc

    String title = "Cash Receipts (Batch Capture)";

    def items = [];
    def orgType;
    def org;
    def orgTypes;
    def currentdate;

    def getOrgList() {
        if(!orgType) return [];
        return orgLookupSvc.getList([orgclass:orgType]); 
    }

    def df = new SimpleDateFormat("yyyy-MM-dd");
    def formatDate = { o->
        if(o instanceof java.util.Date) {
            return df.parse( df.format( o ));
        }
        return df.parse( o );     
    }

    void init() {
        orgTypes = orgSvc.getOrgClasses()*.name;
        currentdate = formatDate( dtsvc.getServerDate() );
    } 

    def model = [
        fetchList: {o-> 
            def appEnv = clientContext.appEnv; 
            def customfolder = appEnv['app.custom']; 
            def homeicon = 'images/' + customfolder + '/home-icon.png';  
            def custom_homeicon = clientContext.getResource(homeicon); 
            if (!custom_homeicon) homeicon = 'home/icons/folder.png';

            def p = [:]; 
            if ( orgType ) p.orgclass = orgType; 
            if ( org ) p.objid = org?.objid; 

            def list = collectionTypeSvc.getBatchCollectionTypes(p).each {
                it.caption = it.title;
                it.icon = homeicon;
            }
            return list;
        }, 
        onOpenItem: {o-> 
            try { 
                def entity = [:];
                entity.collectiontype = o;
                entity.formno = o.formno;
                boolean pass = false;
                def op = InvokerUtil.lookupOpener("afcapture:lookup",[
                    "query.formno" : entity.formno,
                    "query.txnmode": "CAPTURE",
                    onselect:{ m ->
                        entity.collector = m.owner;
                        entity.capturedby = m.assignee;
                        entity.stub = m.stubno;
                        entity.formtype = m.formtype;
                        entity.controlid = m.objid;
                        entity.prefix = m.prefix;
                        entity.suffix = m.suffix;
                        entity.serieslength = m.serieslength;
                        entity.startseries = m.currentseries;
                        entity.endseries = m.endseries; 
                        entity.currentseries = m.currentseries;
                        entity.txnmode = 'CAPTURE';
                        pass = true;
                    }
                ]);
                op.target = "popup";
                Modal.show( op );
                if(!pass) throw new BreakException();    

                //enter the date initial
                pass = false;
                Modal.show( "cashreceipt:specifydate", [ handler:{v->
                    if( formatDate(v.receiptdate).after(currentdate) ) 
                       throw new Exception("Receipt date must not greater than the current date.");                        
                    entity.defaultreceiptdate = v.receiptdate;
                    pass = true;
                }]);
                if(!pass) throw new BreakException();    

                String bh = entity.collectiontype.batchhandler;
                if( !bh ) bh = "misc";
                def opener = Inv.lookupOpener("cashreceipt:batchcapture:postcreate:"+bh, [entity: entity]);  
                opener.target = 'self';
                return opener; 
            } 
            catch(BreakException be) { 
                return null;
            } 
        }
    ] as TileViewModel;
    
}    