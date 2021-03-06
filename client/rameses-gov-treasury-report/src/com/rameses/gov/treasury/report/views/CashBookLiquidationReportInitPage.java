/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rameses.gov.treasury.report.views;

import com.rameses.osiris2.common.ui.ReportInitialTemplatePage;
import com.rameses.rcp.ui.annotations.StyleSheet;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author wflores
 */
@StyleSheet
@Template(value=ReportInitialTemplatePage.class, target="content")
public class CashBookLiquidationReportInitPage extends javax.swing.JPanel {

    /**
     * Creates new form CashBookLiquidationReportInitPage
     */
    public CashBookLiquidationReportInitPage() {
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
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xFormPanel2 = new com.rameses.rcp.control.XFormPanel();
        xComboBox2 = new com.rameses.rcp.control.XComboBox();
        xIntegerField1 = new com.rameses.rcp.control.XIntegerField();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        xLookupField1 = new com.rameses.rcp.control.XLookupField();
        xLabel1 = new com.rameses.rcp.control.XLabel();
        xLookupField2 = new com.rameses.rcp.control.XLookupField();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        xFormPanel1.setCaptionVAlignment(com.rameses.rcp.constant.UIConstants.CENTER);

        xComboBox1.setAllowNull(false);
        xComboBox1.setCaption("Period");
        xComboBox1.setExpression("#{item.name}");
        xComboBox1.setItems("periods");
        xComboBox1.setName("entity.period"); // NOI18N
        xComboBox1.setPreferredSize(new java.awt.Dimension(100, 20));
        xComboBox1.setRequired(true);
        xFormPanel1.add(xComboBox1);

        xFormPanel2.setCaptionPadding(new java.awt.Insets(0, 0, 0, 0));
        xFormPanel2.setCaptionVAlignment(com.rameses.rcp.constant.UIConstants.CENTER);
        xFormPanel2.setOrientation(com.rameses.rcp.constant.UIConstants.HORIZONTAL);
        xFormPanel2.setPadding(new java.awt.Insets(0, 0, 0, 0));
        xFormPanel2.setShowCaption(false);

        xComboBox2.setAllowNull(false);
        xComboBox2.setCaption("Month");
        xComboBox2.setDepends(new String[] {"entity.period"});
        xComboBox2.setExpression("#{item.title}");
        xComboBox2.setItems("reportPeriod.months");
        xComboBox2.setName("entity.month"); // NOI18N
        xComboBox2.setPreferredSize(new java.awt.Dimension(100, 20));
        xComboBox2.setRequired(true);
        xFormPanel2.add(xComboBox2);

        xIntegerField1.setCaption("Year");
        xIntegerField1.setCaptionWidth(60);
        xIntegerField1.setCellPadding(new java.awt.Insets(0, 15, 0, 0));
        xIntegerField1.setDepends(new String[] {"entity.period"});
        xIntegerField1.setName("entity.year"); // NOI18N
        xIntegerField1.setRequired(true);
        xFormPanel2.add(xIntegerField1);

        xFormPanel1.add(xFormPanel2);

        xDateField1.setCaption("Date");
        xDateField1.setDepends(new String[] {"entity.period"});
        xDateField1.setName("entity.date"); // NOI18N
        xDateField1.setRequired(true);
        xFormPanel1.add(xDateField1);

        xLookupField1.setCaption("Account");
        xLookupField1.setExpression("#{item.name}");
        xLookupField1.setHandler("liquidatingofficer:lookup");
        xLookupField1.setName("entity.account"); // NOI18N
        xLookupField1.setPreferredSize(new java.awt.Dimension(277, 20));
        xLookupField1.setRequired(true);
        xFormPanel1.add(xLookupField1);

        xLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180)));
        xLabel1.setCaption("Account");
        xLabel1.setExpression("#{entity.account.name}");
        xLabel1.setName("liqname"); // NOI18N
        xLabel1.setPreferredSize(new java.awt.Dimension(277, 20));
        xFormPanel1.add(xLabel1);

        xLookupField2.setCaption("Fund");
        xLookupField2.setExpression("#{item.title}");
        xLookupField2.setHandler("fund:lookup");
        xLookupField2.setName("entity.fund"); // NOI18N
        xLookupField2.setPreferredSize(new java.awt.Dimension(277, 20));
        xLookupField2.setRequired(true);
        xFormPanel1.add(xLookupField2);

        add(xFormPanel1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XComboBox xComboBox2;
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XFormPanel xFormPanel2;
    private com.rameses.rcp.control.XIntegerField xIntegerField1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLookupField xLookupField1;
    private com.rameses.rcp.control.XLookupField xLookupField2;
    // End of variables declaration//GEN-END:variables
}
