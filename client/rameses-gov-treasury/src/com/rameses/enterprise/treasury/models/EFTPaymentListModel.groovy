package com.rameses.enterprise.treasury.models;

import com.rameses.rcp.annotations.*;
import com.rameses.seti2.models.CrudListModel;

public class EFTPaymentListModel extends CrudListModel {

    def sorttype;
    def sorttypes = ['ASC','DESC'];
    
    def sortfield;
    def sortfields = [
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
        else if (selectedNode?.state.toString().toUpperCase() == 'POSTED' ) {
            buff.append("refdate DESC"); 
        }
        else {
            buff.append("refdate ASC"); 
        }
        return buff.toString(); 
    }
} 