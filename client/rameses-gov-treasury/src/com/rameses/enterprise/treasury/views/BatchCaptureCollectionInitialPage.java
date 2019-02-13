/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rameses.enterprise.treasury.views;

import com.rameses.osiris2.themes.FormPage;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author elmonazareno
 */
@Template(FormPage.class)
public class BatchCaptureCollectionInitialPage extends javax.swing.JPanel {

    /**
     * Creates new form BatchCaptureCollectionInitialPage
     */
    public BatchCaptureCollectionInitialPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        xIntegerField1 = new com.rameses.rcp.control.XIntegerField();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        xButton1 = new com.rameses.rcp.control.XButton();

        xFormPanel1.setCaptionVAlignment(com.rameses.rcp.constant.UIConstants.CENTER);
        xFormPanel1.setCaptionWidth(150);

        xIntegerField1.setCaption("Start Series");
        xIntegerField1.setName("startseries"); // NOI18N
        xIntegerField1.setCaptionFontStyle("font-size:14;");
        xIntegerField1.setFontStyle("font-size:14;");
        xIntegerField1.setPreferredSize(new java.awt.Dimension(0, 22));
        xFormPanel1.add(xIntegerField1);

        xDateField1.setCaption("Receipt Date");
        xDateField1.setName("startdate"); // NOI18N
        xDateField1.setCaptionFontStyle("font-size:14;");
        xDateField1.setFontStyle("font-size:14;");
        xDateField1.setPreferredSize(new java.awt.Dimension(0, 22));
        xFormPanel1.add(xDateField1);

        xButton1.setCaption(" ");
        xButton1.setMnemonic('N');
        xButton1.setName("doNext"); // NOI18N
        xButton1.setCellPadding(new java.awt.Insets(20, 0, 0, 0));
        xButton1.setDefaultCommand(true);
        xButton1.setFontStyle("font-size: 14;");
        xButton1.setMargin(new java.awt.Insets(5, 20, 5, 20));
        xButton1.setText("Next");
        xFormPanel1.add(xButton1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(xFormPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(xFormPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(282, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XButton xButton1;
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XIntegerField xIntegerField1;
    // End of variables declaration//GEN-END:variables
}
