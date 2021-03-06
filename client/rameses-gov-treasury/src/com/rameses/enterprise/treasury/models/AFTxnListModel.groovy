package com.rameses.enterprise.treasury.models;

import com.rameses.seti2.models.CrudListModel;

class AFTxnListModel extends CrudListModel {

    public void initColumn( c ) { 
        if ( c.name == 'controlno') {
            c.width = 100; 
            c.maxWidth = 140; 
        }
        else if ( c.name == 'dtfiled') {
            c.width = 100; 
            c.maxWidth = 120; 
            c.type = "date";
            c.outputFormat = "yyyy-MM-dd";
        }
        else if ( c.name == 'txndate') {
            c.width = 120; 
            c.maxWidth = 140; 
            c.type = "date";
            c.outputFormat = "yyyy-MM-dd HH:mm:ss";
        }
        else if ( c.name == 'txntype') {
            c.width = 120; 
            c.maxWidth = 150;
        }
    }     
}    