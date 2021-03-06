package com.rameses.enterprise.treasury.models;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.seti2.models.*;
import com.rameses.rcp.framework.ValidatorException;

class RemittanceFundModel extends CrudFormModel {

    @Service("RemittanceService")
    def service;
    
    boolean showCashBreakdown = true;
    boolean showCreditMemos = true;
    
    def checkList = [];
    def creditMemoList = [];
    
    void afterOpen() {
        def p = [remittanceid: entity.remittanceid, fundid: entity.fund.objid ]
        //build summaryList
        def m = [_schemaname: 'vw_cashreceiptpayment_noncash' ];
        m.select = "refid,refno,reftype,refdate,particulars,amount:{SUM(amount)}";
        m.groupBy = "refid,refno,reftype,refdate,particulars";
        m.orderBy = "refdate,refno";
        m.where = [ "remittanceid = :remittanceid AND fund.objid = :fundid AND amount > 0 AND voided=0 ", p ];
         
        def list = queryService.getList( m ); 
        checkList = list.findAll{ it.reftype == 'CHECK' };
        creditMemoList = list.findAll{ it.reftype != 'CHECK' };
        if( entity.totalcheck + entity.totalcash == 0 ) showCashBreakdown = false;
        if( entity.totalcr == 0 ) showCreditMemos = false;
    }
    
    def checkModel = [
        fetchList: {o ->
            return checkList;
        },
        onOpenItem: {o,col->
            def op = Inv.lookupOpener("checkpayment:open", [entity: [objid: o.refid ]] );
            op.target = "popup";
            return op;
        }
    ] as BasicListModel; 
    
    def creditMemoModel = [
        fetchList: {o -> 
            return creditMemoList;
        }
    ] as BasicListModel;
    
    
    def getPrintFormData() { 
        def rdata = service.getReportData([ objid: entity.remittanceid, fund: entity.fund ]); 
        if ( rdata ) { 
            def amount = rdata.amount; 
            def totalcheck = rdata.totalcheck;
            def totalcr = rdata.totalcr;
            
            rdata.putAll( entity ); 
            rdata.amount = amount; 
            rdata.totalcr = totalcr; 
            rdata.totalcheck = totalcheck;
            rdata.totalnoncash = totalcheck + totalcr;
            rdata.remittancedate = rdata.controldate;
            rdata.txnno = rdata.controlno; 
        }         
        return rdata;
    } 
    
    def doCancel() {
        return "_close";
    }
    
    def doOk() {
        def m = [objid:entity.objid, remittanceid:entity.remittanceid, cashbreakdown:entity.cashbreakdown ];
        service.updateRemittanceFundBreakdown( m )
        return "_close";
    }
    
}    