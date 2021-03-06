/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rameses.enterprise.treasury.views;

import com.rameses.osiris2.themes.FormPage;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author Elmo Nazareno
 */
@Template(FormPage.class)
public class AFTxnInitialPage extends javax.swing.JPanel {

    /**
     * Creates new form AFIRInitialPage
     */
    public AFTxnInitialPage() {
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

        xFormPanel2 = new com.rameses.rcp.control.XFormPanel();
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        jPanel1 = new javax.swing.JPanel();
        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        xRadio2 = new com.rameses.rcp.control.XRadio();
        xRadio1 = new com.rameses.rcp.control.XRadio();
        xLookupField1 = new com.rameses.rcp.control.XLookupField();
        xLabel1 = new com.rameses.rcp.control.XLabel();

        xFormPanel2.setCaptionVAlignment(com.rameses.rcp.constant.UIConstants.CENTER);
        xFormPanel2.setCaptionWidth(150);
        xFormPanel2.setPadding(new java.awt.Insets(0, 10, 5, 5));

        xComboBox1.setCaption("Select Type of Transaction");
        xComboBox1.setExpression("#{ item.caption }");
        xComboBox1.setItemKey("name");
        xComboBox1.setItems("txnTypes");
        xComboBox1.setName("txntype"); // NOI18N
        xComboBox1.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel2.add(xComboBox1);

        xDateField1.setCaption("Date Issued");
        xDateField1.setName("dtfiled"); // NOI18N
        xDateField1.setRequired(true);
        xFormPanel2.add(xDateField1);

        xFormPanel1.setDepends(new String[] {"txntype"});
        xFormPanel1.setVisibleWhen("#{ txntype!=null && txntype.matches( 'PURCHASE_RECEIPT|ISSUE' ) }");
        xFormPanel1.setCaptionVAlignment(com.rameses.rcp.constant.UIConstants.CENTER);
        xFormPanel1.setShowCaption(false);

        xRadio2.setName("withrequest"); // NOI18N
        xRadio2.setOptionValue(false);
        xRadio2.setShowCaption(false);
        xRadio2.setText("Without Request");
        xFormPanel1.add(xRadio2);

        xRadio1.setName("withrequest"); // NOI18N
        xRadio1.setOptionValue(true);
        xRadio1.setShowCaption(false);
        xRadio1.setText("With Request");
        xFormPanel1.add(xRadio1);

        xLookupField1.setCaption("Enter Request No");
        xLookupField1.setDepends(new String[] {"withrequest"});
        xLookupField1.setExpression("#{ afrequest.reqno }");
        xLookupField1.setHandler("lookupRequest");
        xLookupField1.setName("afrequest"); // NOI18N
        xLookupField1.setVisibleWhen("#{ withrequest == true }");
        xLookupField1.setCaptionWidth(150);
        xLookupField1.setCellPadding(new java.awt.Insets(0, 30, 0, 0));
        xLookupField1.setPreferredSize(new java.awt.Dimension(0, 20));
        xLookupField1.setRequired(true);
        xFormPanel1.add(xLookupField1);

        xLabel1.setCaption("Requested By");
        xLabel1.setDepends(new String[] {"withrequest", "afrequest"});
        xLabel1.setExpression("#{ afrequest.requester.name }");
        xLabel1.setVisibleWhen("#{ withrequest == true }");
        xLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180)));
        xLabel1.setCaptionWidth(150);
        xLabel1.setCellPadding(new java.awt.Insets(0, 30, 0, 0));
        xLabel1.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xLabel1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(xFormPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 68, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xFormPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(xFormPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(579, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xFormPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XFormPanel xFormPanel2;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLookupField xLookupField1;
    private com.rameses.rcp.control.XRadio xRadio1;
    private com.rameses.rcp.control.XRadio xRadio2;
    // End of variables declaration//GEN-END:variables
}
