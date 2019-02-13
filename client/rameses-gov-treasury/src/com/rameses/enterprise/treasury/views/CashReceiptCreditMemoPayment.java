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
public class CashReceiptCreditMemoPayment extends javax.swing.JPanel {

    /**
     * Creates new form CashReceiptCreditMemoPayment
     */
    public CashReceiptCreditMemoPayment() {
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

        xFormPanel3 = new com.rameses.rcp.control.XFormPanel();
        xLookupField1 = new com.rameses.rcp.control.XLookupField();
        xLabel8 = new com.rameses.rcp.control.XLabel();
        xLabel3 = new com.rameses.rcp.control.XLabel();
        xLabel4 = new com.rameses.rcp.control.XLabel();
        xLabel6 = new com.rameses.rcp.control.XLabel();
        xLabel5 = new com.rameses.rcp.control.XLabel();
        xLabel2 = new com.rameses.rcp.control.XLabel();

        xFormPanel3.setCaptionFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        xFormPanel3.setCaptionWidth(180);

        xLookupField1.setCaption("Credit Memo Ref No");
        xLookupField1.setExpression("#{entry.refno} ");
        xLookupField1.setHandler("lookupCreditMemo");
        xLookupField1.setName("entry.refno"); // NOI18N
        xLookupField1.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel3.add(xLookupField1);

        xLabel8.setCaption("Ref Date");
        xLabel8.setExpression("#{entry.refdate} ");
        xLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        xLabel8.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel3.add(xLabel8);

        xLabel3.setCaption("Bank Details");
        xLabel3.setExpression("#{entry.bankaccount.code} - #{entry.bankaccount.bank.name}");
        xLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        xLabel3.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel3.add(xLabel3);

        xLabel4.setCaption("Payer");
        xLabel4.setExpression("#{entry.payer.name} ");
        xLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        xLabel4.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel3.add(xLabel4);

        xLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        xLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        xLabel6.setCaption("Particulars");
        xLabel6.setExpression("#{entry.particulars} ");
        xLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        xLabel6.setPreferredSize(new java.awt.Dimension(0, 40));
        xFormPanel3.add(xLabel6);

        xLabel5.setCaption("Amount");
        xLabel5.setExpression("#{entry.amount} ");
        xLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        xLabel5.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel3.add(xLabel5);

        xLabel2.setCaption("Fund");
        xLabel2.setDepends(new String[] {"check"});
        xLabel2.setExpression("#{ entry.fund.title }");
        xLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        xFormPanel3.add(xLabel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xFormPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xFormPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XFormPanel xFormPanel3;
    private com.rameses.rcp.control.XLabel xLabel2;
    private com.rameses.rcp.control.XLabel xLabel3;
    private com.rameses.rcp.control.XLabel xLabel4;
    private com.rameses.rcp.control.XLabel xLabel5;
    private com.rameses.rcp.control.XLabel xLabel6;
    private com.rameses.rcp.control.XLabel xLabel8;
    private com.rameses.rcp.control.XLookupField xLookupField1;
    // End of variables declaration//GEN-END:variables
}
