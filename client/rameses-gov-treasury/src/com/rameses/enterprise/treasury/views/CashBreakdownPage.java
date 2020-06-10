/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rameses.enterprise.treasury.views;

import com.rameses.osiris2.themes.OKCancelPage;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author Elmo Nazareno
 */
@Template(OKCancelPage.class)
public class CashBreakdownPage extends javax.swing.JPanel {

    /**
     * Creates new form CashBreakdownPage
     */
    public CashBreakdownPage() {
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

        jPanel1 = new javax.swing.JPanel();
        cashDenomination1 = new com.rameses.enterprise.treasury.components.CashDenomination();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Cash Breakdown");
        setBorder(xTitledBorder1);
        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        cashDenomination1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        cashDenomination1.setAmountField("total");
        cashDenomination1.setName("cashbreakdown"); // NOI18N
        cashDenomination1.setOpaque(false);
        jPanel1.add(cashDenomination1, java.awt.BorderLayout.WEST);

        add(jPanel1, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.enterprise.treasury.components.CashDenomination cashDenomination1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
