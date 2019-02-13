package com.rameses.gov.treasury.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;

class CollectionSummaryModel {

    @Binding
    def binding;
    
    @Service('CollectionSummaryService')
    def svc;
    
    def entity;
    
    def receiptCount = 0;
    def totalCollection = 0.0;
    def cashTendered = 0.0;
    def change = 0.0;
    def items; 
    
    String title = 'Collection Summary Information'
    
    @PropertyChangeListener
    def listener = [
        'receiptCount' : { getReceiptSummary() },
        'cashTendered' : { calcChange() },
    ] 
    
    
    void getReceiptSummary(){
        if (receiptCount == null || receiptCount == 0){
            clearInfo();
        }
        else {
            totalCollection = 0.0;
            
            items = svc.getCollectionsByCount(receiptCount)
            
            if( items )
                totalCollection = items.amount.sum();
                
            cashTendered = 0.0;
            change = 0.0;
            listHandler.load();
            binding?.refresh();
            binding?.focus('cashTendered');
        }
    }
    
    void clearInfo(){
        totalCollection = 0.0;
        cashTendered = 0.0;
        change = 0.0;
        items = [];
        listHandler.reload();
        binding?.refresh();
    }
    
    void calcChange(){
        change = 0.0
        if (cashTendered == null ) cashTendered = 0.0
        if ( cashTendered > totalCollection )
            change = cashTendered - totalCollection;
        binding?.refresh();
    }
    
    def listHandler = [
        fetchList : { return items }
    ] as BasicListModel

}