package com.rameses.enterprise.treasury.models;

import com.rameses.rcp.annotations.*;
import com.rameses.seti2.models.CrudListModel;

public class CreditMemoListModel extends CrudListModel {

    def sorttype;
    def sorttypes = ['ASC','DESC'];
    
    def sortfield;
    def sortfields = [
        [objid: 'controlno', title: 'ControlNo'],
        [objid: 'dtissued', title: 'TxnDate'],
        [objid: 'refno', title: 'ReferenceNo'],
        [objid: 'refdate', title: 'ReferenceDate'],
        [objid: 'payer.name', title: 'Payer Name']
    ];
    
    public String getOrderBy() {
        def buff = new StringBuilder(); 
        if ( sortfield ) { 
            buff.append(" ").append( sortfield.objid );
            if ( sorttype ) buff.append(" ").append( sorttype ); 
        }
        else if (selectedNode?.state.toString().toUpperCase() == 'OPEN' ) {
            buff.append("dtissued ASC"); 
        }
        else {
            buff.append("dtissued DESC"); 
        }
        return buff.toString(); 
    }
} 