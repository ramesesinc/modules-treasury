package com.rameses.enterprise.treasury.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.rcp.util.UIControlUtil;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.seti2.models.*;
import com.rameses.rcp.framework.ValidatorException;

class RemittanceModel extends CrudFormModel {

    @Service("RemittanceService")
    def remSvc;    

    @Service("Var")
    def var;
    
    @SubWindow 
    def subWin;
    
    @Script("User")
    def user;    

    //this is passed 
    def handler;
    def selectedFund;
    def selectedAf;
    
    def numformat = new java.text.DecimalFormat("#,##0.00"); 
    
    String getWindowTitle() {
        if ( entity.state.toString().toUpperCase() == 'DRAFT' ) {
            return super.getWindowTitle(); 
        } else {
            return entity.controlno.toString();
        }
    }
    
    def getFormattedAmount() {
        if ( !(entity.amount instanceof Number )) {
            entity.amount = 0.0; 
        } 
        return numformat.format( entity.amount );  
    }
    
    def getTotalNoncash() {
        return (entity.totalcheck + entity.totalcr);
    }
    
    def fundSummaryHandler = [
        fetchList: { o->
            def m = [_schemaname: 'remittance_fund' ];
            m.findBy = [ remittanceid: entity.objid ];
            return queryService.getList( m );
        },
        onOpenItem: {o,col->
            return viewFundEntry();
        }
    ] as BasicListModel;
    
    def viewFundEntry() {
        if(!selectedFund) throw new Exception("Please select a fund");
        return Inv.lookupOpener("remittance_fund:open", [entity : selectedFund]  );
    }
    
    boolean getCashBreakdownByFund() {
        def b = var.getProperty("remittance_breakdown_byfund", "false" );
        if( b.equals("1")) b = "true";
        else if( b.equals("0")) b = "false";
        return Boolean.parseBoolean(b+"");
    }
    
    def updateCashByRemittance() {
        def p = [total: entity.amount - totalNoncash, cashbreakdown: entity.cashbreakdown ];
        p.handler = { o->
            def m = [_schemaname: 'remittance'];
            m.findBy = [objid: entity.objid];
            m.cashbreakdown = o.cashbreakdown;
            m.totalcash = o.cashbreakdown.sum{ it.amount };
            persistenceService.update( m );
            entity.cashbreakdown = m.cashbreakdown;
            entity.totalcash = m.totalcash;
            binding.refresh();
        }
        return Inv.lookupOpener("cashbreakdown", p );
    }
    
    def updateCashByFund() {
        if(!selectedFund) throw new Exception("Please select a fund entry");
        if(selectedFund.totalcash == 0  ) 
            throw new Exception("There is no cash remittance for selected item");
        def p = [total: selectedFund.totalcash, cashbreakdown: selectedFund.cashbreakdown ];
        p.handler = { o->
            def u = [objid:selectedFund.objid, remittanceid: entity.objid, totalcash: o.total, cashbreakdown: o.cashbreakdown ]; 
            remSvc.updateCash( u );
            fundSummaryHandler.reload();
            binding.refresh();
        }
        return Inv.lookupOpener("cashbreakdown", p );
    }
    
    def afSummaryHandler = [
        fetchList: { o->
           def m = [_schemaname: 'vw_remittance_cashreceipt_afsummary' ];
           m.findBy = [ remittanceid: entity.objid ];
           m.orderBy = 'formno, startseries, stubno'; 
           def list = queryService.getList( m ); 
           list.each{ 
               it.amount = it.amount - it.voidamt; 
           }
           return list; 
        },
        onOpenItem: {o,col->
            return viewReceipts();
        }
    ] as BasicListModel;
    
    def viewReceipts() {
        if(!selectedAf) throw new Exception("Please select an entry");  
        def o = selectedAf;
        def p = [:];
        p.put( "query.afcontrolid", o.controlid );
        p.put( "query.fromseries", o.fromseries );
        p.put( "query.toseries", o.toseries );
        return Inv.lookupOpener("cashreceipt_list:afseries", p );
    }
    
    def checkModel = [
        fetchList: { o->
            def m = [_schemaname: 'vw_cashreceiptpayment_noncash' ];
            m.select = "refid,refno,reftype,refdate,particulars,amount:{SUM(amount)}";
            m.groupBy = "refid,refno,reftype,refdate,particulars";
            m.orderBy = "refdate,refno";
            m.where = [ "remittanceid = :rid AND voided=0 AND amount > 0", [rid: entity.objid ]];
            return queryService.getList( m );
        },
        onOpenItem: {o,col->
            def op = Inv.lookupOpener("checkpayment:open", [entity: [objid: o.refid ]] );
            op.target = "popup";
            return op;
        }
    ] as BasicListModel;
    
   
    /*
    def openPreview() {
        println 'open preview';
        open();
        return preview("remittance:form_report");
    }
    */
    
    void remit() {
        if ( MsgBox.confirm('You are about to submit this for liquidation. Proceed?')) {
            entity = remSvc.submitForLiquidation( entity ); 
            if ( subWin ) subWin.update(); 
        }
    }
    
    public void afterOpen() { 
        [ fundSummaryHandler, checkModel ].each{
            try {
                it.reload(); 
            } catch(Throwable t) {
                MsgBox.err( t ); 
            }
        } 
    }

    
    def popupReports( inv ) {
        def popupMenu = new PopupMenuOpener();
        def list = Inv.lookupOpeners( inv.properties.category, [entity:entity] );
        list.each{
            popupMenu.add( it );
        }
        return popupMenu;
    }    
    
    def decFormat = new java.text.DecimalFormat('0.00');     
    def getPrintFormData() { 
        def data = remSvc.getReportData([ objid: entity.objid ]);
        def list = data.cashbreakdown; 
        list.each{
            it.indexno = ((Number) (it.denomination ? it.denomination : 0)).intValue(); 
        }
        list.sort{ -it.indexno }
        list.each { 
            it.caption = it.denomination.toString(); 
            if ( it.denomination instanceof Number ) {
                it.caption = decFormat.format( it.denomination ); 
            }
        } 
        data.cashbreakdown = list; 
        return data; 
    } 
    
    def getReportForm() { 
        def path = 'com/rameses/gov/treasury/remittance/report/rcd'; 
        return [
            mainreport: path + '/rcd_main.jasper', 
            subreports: [
                [name: 'CollectionType', template: path + '/collectiontype.jasper'], 
                [name: 'CollectionSummary', template: path + '/collectionsummary.jasper'], 
                [name: 'RemittedForms', template: path + '/remittedforms.jasper'], 
                [name: 'NonSerialRemittances', template: path + '/nonserialremittances.jasper'], 
                [name: 'NonSerialSummary', template: path + '/nonserialsummary.jasper'], 
                [name: 'OtherPayments', template: path + '/otherpayments.jasper'], 
                [name: 'Denomination', template: path + '/denomination.jasper'], 
                [name: 'CancelSeries', template: path + '/cancelseries.jasper']
            ]
        ];
    } 

    
    boolean isActionAllowedOnOpenState() {
        if ( user == null ) return false; 
        if ( !entity.state.toString().toUpperCase().matches('OPEN')) return false; 
        return ( entity.collector?.objid == user.userid ); 
    }
    boolean isActionAllowedOnDraftState() {
        if ( user == null ) return false; 
        if ( !entity.state.toString().toUpperCase().matches('DRAFT')) return false; 
        return ( entity.collector?.objid == user.userid ); 
    }
    
    def delete() {
        boolean b = MsgBox.confirm('You are about to delete this transaction. Proceed?'); 
        if ( !b ) return null; 
        
        def mm = [_schemaname: 'remittance', objid: entity.objid]; 
        persistenceService.removeEntity( mm ); 
        return '_close'; 
    }
    
    
    
    def issuedAF; 
    def issuedAFHandler = [
        fetchList: { o-> 
            return entity.afsummaries.findAll{ it.indexlevel == 0 } 
        }
    ] as DataListModel; 
    
    def unissuedAF; 
    def unissuedAFHandler = [
        fetchList: { o-> 
            return entity.afsummaries.findAll{ it.indexlevel == 1 } 
        }
    ] as DataListModel; 
    
    def viewIssuedAF() { 
        return viewAF( issuedAF ); 
    }
    def viewIssuedReceipts() { 
        return viewReceipts( issuedAF ); 
    }
    
    def viewUnissuedAF() { 
        return viewAF( unissuedAF ); 
    }
    def viewUnissuedReceipts() { 
        return viewReceipts( unissuedAF ); 
    }
    
    def viewAF( o ) { 
        def param = [entity: [:]];
        param.entity.objid = o.controlid; 
        def op = Inv.lookupOpener('af_control:open', param); 
        op.target = 'popup'; 
        op.properties.width = 1024; 
        op.properties.height = 600; 
        return op; 
    }
    
    def viewReceipts( o ) {
        def namebuff = new StringBuilder('cashreceipt_list:remitted:view'); 
        
        def wheremap = [controlid: o.controlid]; 
        def wheresql = new StringBuilder('controlid = :controlid'); 
        if ( o.qtyissued > 0 ) {
            wheresql.append(" AND remittanceid = :remittanceid"); 
            wheremap.remittanceid = o.remittanceid;
        }
        else {
            namebuff.append('previous'); 
        }
        
        def param = [:]; 
        param.customFilter = [wheresql, wheremap]; 
        def op = Inv.lookupOpener( namebuff.toString(), param );
        op.target = "popup";
        return op;
    }
    
    def popupActions( inv ) { 
        def param = [ entity: entity ]; 
        param.refreshHandler = {
            reloadEntity(); 
        }

        try {
            def boolean has_visible_items = false;
            def popupMenu = new PopupMenuOpener(); 
            def list = Inv.lookupOpeners( inv.properties.category, param );
            list.each{ 
                boolean visible = true; 
                if ( it.properties.visibleWhen ) {
                    try {
                        visible = UIControlUtil.evaluateExprBoolean( this, it.properties.visibleWhen );
                    } catch(Throwable t) {
                        visible = false; 
                    }
                }
                if ( visible ) {
                    popupMenu.add( it );
                    has_visible_items = true; 
                }
            }
            if ( !has_visible_items ) {
                MsgBox.alert('No available actions'); 
                return null; 
            }
            return popupMenu; 
        } 
        catch(Throwable t) {
            MsgBox.alert('No available actions'); 
            return null; 
        }
    } 
} 