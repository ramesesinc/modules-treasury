package com.rameses.gov.treasury.tool.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

class RemittanceExportDataModel {

    @Service("RemittanceImportExportService")
    def exportSvc;    
        
    def entity;
    
    void init() { 
        def chooser = new javax.swing.JFileChooser();
        chooser.setSelectedFile(new java.io.File(entity.controlno + '.rem'));
        int opt = chooser.showSaveDialog(null);
        if ( opt == 0 ) {
            com.rameses.io.FileUtil.writeObject( chooser.selectedFile, exportSvc.exportRemittance( entity.objid ));
            MsgBox.alert("Remittance has been successfully exported!");
        } 
    }    
}