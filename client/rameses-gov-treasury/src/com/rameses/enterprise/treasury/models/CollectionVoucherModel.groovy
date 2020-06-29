package com.rameses.enterprise.treasury.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.rcp.util.UIControlUtil;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.seti2.models.CrudFormModel;

class CollectionVoucherModel extends CrudFormModel {

    @Service("CollectionVoucherService")
    def collSvc;    
    
    @Service("DepositVoucherService")
    def depositSvc; 
    
    @Script("User")
    def user;    
    
    def selectedRemittance;
    def selectedFund;
    
    def numformat = new java.text.DecimalFormat("#,##0.00"); 
    
    String getWindowTitle() {
        if ( entity.state.toString().toUpperCase() == 'DRAFT' ) {
            return super.getWindowTitle(); 
        } else {
            return entity.controlno.toString();
        }
    }
    
    def getTotalNoncash() {
        return (entity.totalcheck + entity.totalcr);
    }
    
    def getFormattedAmount() {
        if ( !(entity.amount instanceof Number )) {
            entity.amount = 0.0; 
        } 
        return numformat.format( entity.amount );  
    }
    
    def remittanceListHandler = [
        fetchList: { o->
            def m = [_schemaname:'remittance'];
            m.findBy = [collectionvoucherid: entity.objid ];
            return queryService.getList( m );
        },
        onOpenItem: { o,col->
            return viewRemittance();
        }
    ] as BasicListModel;
    
    def viewRemittance() {
        if( !selectedRemittance ) throw new Exception("Please select an item")
        def o = selectedRemittance;
        def op = Inv.lookupOpener("remittance:open", [entity: o]);
        op.target = "window";
        return op;
    }
    
    def fundSummaryHandler = [
        fetchList: { o->
            def m = [_schemaname:'collectionvoucher_fund'];
            m.findBy = [parentid: entity.objid ];
            return queryService.getList( m );
        },
        onOpenItem: { o,col->
            return viewFund();
        }
    ] as BasicListModel;
    
    def fundTransferHandler = [
        fetchList: { o->
            def m = [_schemaname:'collectionvoucher_fund_transfer'];
            m.findBy = [parentid: entity.objid];
            return queryService.getList( m );
        },
        onOpenItem: { o,col->
            //
        }
    ] as BasicListModel;
    
    def viewFund() {
        if( !selectedFund ) throw new Exception("Please select an item")
        def o = selectedFund;
        def op = Inv.lookupOpener("collectionvoucher_fund:open", [entity: o]);
        op.target = "popup";
        return op;
    }
    
    def selectedCheck;
    def checkModel = [
        fetchList: { o->
            def m = [_schemaname: 'vw_cashreceiptpayment_noncash_liquidated' ];
            m.select = "reftype,refno,refdate,particulars,amount:{SUM(amount)}";
            m.groupBy = "reftype,refno,refdate,particulars";
            m.orderBy = "refdate,refno";
            m.where = [ "collectionvoucherid = :cvid AND voided=0 AND amount > 0", [cvid: entity.objid ]];
            return queryService.getList( m );
        }
    ] as BasicListModel;
    
    public void afterOpen() { 
        [ fundSummaryHandler, checkModel, remittanceListHandler ].each{
            try {
                it.reload(); 
            } catch(Throwable t) {
                MsgBox.err( t ); 
            }
        } 
    }
    
    def post() {
        if(!MsgBox.confirm("You are about to post this transaction. Proceed?")) return null;
        def o = collSvc.post( entity ); 
        if ( o ) entity.putAll( o );  
        
        if ( subWindow ) subWindow.update(); 
    }
    
    def decFormat = new java.text.DecimalFormat('0.00'); 
    def getPrintFormData() { 
        def rdata = collSvc.getReportData([ objid: entity.objid ]); 
        if ( rdata ) rdata.putAll( entity ); 
        
        rdata.otherpayments = checkModel.fetchList([:]); 
        
        def list = rdata.cashbreakdown; 
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
        rdata.cashbreakdown = list; 
        return rdata;
    } 
    
    def getReportForm() { 
        def path = 'com/rameses/gov/treasury/liquidation/report/rcd'; 
        return [
            mainreport: path + '/main.jasper', 
            subreports: [
                [name:"remittances", template: path + "/remittances.jasper"],
                [name:"collectionsummary", template: path + "/collectionsummary.jasper"],
                [name:"reportaforms", template: path + "/reportaforms.jasper"],
                [name:"reportnsaforms", template: path + "/reportnsaforms.jasper"],
                [name:"reportnonserialsummary", template: path + "/reportnonserialsummary.jasper"],
                [name:"OtherPayments", template: path + "/otherpayments.jasper"],
                [name:"Denomination", template: path + "/denomination.jasper"]
            ], 
            parameters: [ REPORTTYPE: 'SUMMARY' ] 
        ] 
    } 
    
    def popupReports( inv ) {
        def popupMenu = new PopupMenuOpener();
        def list = Inv.lookupOpeners( inv.properties.category, [entity:entity] );
        list.each{
            popupMenu.add( it );
        }
        return popupMenu;
    } 
    
    
    boolean isActionAllowedOnOpenState() {
        if ( user == null ) return false; 
        if ( !entity.state.toString().toUpperCase().matches('OPEN')) return false; 
        return ( entity.liquidatingofficer?.objid == user.userid ); 
    }
    boolean isActionAllowedOnPostedState() {
        if ( user == null ) return false; 
        if ( !entity.state.toString().toUpperCase().matches('POSTED')) return false; 
        return ( entity.liquidatingofficer?.objid == user.userid ); 
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
                        t.printStackTrace(); 
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
            t.printStackTrace();
            MsgBox.alert('No available actions'); 
            return null; 
        }
    }     
} 