package com.rameses.enterprise.treasury.models;

import com.rameses.rcp.common.*;
import com.rameses.rcp.annotations.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*; 
import com.rameses.util.*;
import java.rmi.server.*;

class CashReceiptEFTPaymentModel  { 

    @Service('QueryService')
    def querySvc; 
    
    def entity;
    def fundList;
    def saveHandler;
    def eft;
    
    void init() {
        def fundids = fundList?.collect{ it.fund?.objid }.findAll{( it )}.unique(); 
        if ( !fundids ) throw new Exception('Please specify at least one fund'); 

        // fix the problem of the receipt amount, max decimal places is 2 only        
        def numformat = new java.text.DecimalFormat('0.00'); 
        entity.amount = new BigDecimal( numformat.format( entity.amount )); 

        // fix the problem of the fund amounts, max decimal places is 2 only
        fundList.each{
            it.amount = new BigDecimal( numformat.format( it.amount )); 
        }
        def diffamt = entity.amount - fundList.sum{ it.amount } 
        if ( diffamt > 0 ) fundList.last().amount += diffamt; 

        
        def qparam = [_schemaname: 'fund', where: []]; 
        qparam.select = 'objid,depositoryfundid'; 
        qparam.where << "objid IN ('"+ fundids.join("','") +"')"; 
        fundids = querySvc.getList( qparam ).collect{( it.depositoryfundid ? it.depositoryfundid : it.objid )}.unique(); 
        
        def h = { o->
            if(o.amount!=entity.amount ) { 
                println 'receipt-amount: ' + entity.amount +', eft-amount: '+ o.amount; 
                throw new Exception("Amount to pay must match the EFT Amount");
            }
            eft = o;
        }
        Modal.show( "eftpayment_unused:lookup", [fundids: fundids, onselect: h] );
        if( !eft ) throw new BreakException();
    } 
    
    def doOk() {
        def payments = [];
        fundList.each {
            def m = [:];
            m.item = eft;
            m.refid = eft.objid;
            m.reftype =  "EFT"; 
            m.amount = it.amount;
            m.particulars = eft.refno + " (" + eft.bankaccount.code + " - " + eft.bankaccount.bank.name + ") dated " + eft.refdate; 
            m.refno = eft.refno;
            m.refdate = eft.refdate;
            m.fund = it.fund;
            payments << m;
        }
        def retval = [:];
        retval.paymentitems = payments;
        retval.eft = eft;
        saveHandler( retval );
        return "_close";
    }
    
    def doCancel() {
        return "_close";
    }
    
} 