/*
 * CashticketControlPage.java
 *
 * Created on October 6, 2013, 8:14 AM
 */

package com.rameses.gov.treasury.cashticket;

import com.rameses.osiris2.themes.FormPage;
import com.rameses.rcp.ui.annotations.StyleSheet;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  arnel
 */
@Template(FormPage.class)
@StyleSheet
public class CashticketControlPage extends javax.swing.JPanel {
    
    /**
     * Creates new form CashticketControlPage
     */
    public CashticketControlPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xComboBox2 = new com.rameses.rcp.control.XComboBox();
        xLabel1 = new com.rameses.rcp.control.XLabel();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        xNumberField1 = new com.rameses.rcp.control.XNumberField();
        xIntegerField1 = new com.rameses.rcp.control.XIntegerField();

        formPanel1.setCaptionVAlignment(com.rameses.rcp.constant.UIConstants.CENTER);
        formPanel1.setCaptionWidth(120);
        formPanel1.setPadding(new java.awt.Insets(5, 15, 5, 15));

        xComboBox1.setCaption("Collector");
        xComboBox1.setExpression("#{item.name}");
        xComboBox1.setItems("collectorlist");
        xComboBox1.setName("entity.collector"); // NOI18N
        xComboBox1.setPreferredSize(new java.awt.Dimension(0, 22));
        xComboBox1.setRequired(true);
        formPanel1.add(xComboBox1);

        xComboBox2.setCaption("AF No. ");
        xComboBox2.setExpression("#{item.objid}");
        xComboBox2.setItems("formTypes");
        xComboBox2.setName("entity.formtype"); // NOI18N
        xComboBox2.setPreferredSize(new java.awt.Dimension(150, 22));
        xComboBox2.setRequired(true);
        formPanel1.add(xComboBox2);

        xLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180)));
        xLabel1.setCaption("AF Unit");
        xLabel1.setDepends(new String[] {"entity.formtype"});
        xLabel1.setExpression("#{entity.formtype.unit}");
        xLabel1.setPreferredSize(new java.awt.Dimension(150, 20));
        formPanel1.add(xLabel1);

        xDateField1.setCaption("Date Issued");
        xDateField1.setName("entity.refdate"); // NOI18N
        xDateField1.setPreferredSize(new java.awt.Dimension(150, 20));
        xDateField1.setRequired(true);
        formPanel1.add(xDateField1);

        xNumberField1.setCaption("Stub No");
        xNumberField1.setFieldType(Integer.class);
        xNumberField1.setName("entity.stubno"); // NOI18N
        xNumberField1.setPreferredSize(new java.awt.Dimension(150, 20));
        xNumberField1.setRequired(true);
        formPanel1.add(xNumberField1);

        xIntegerField1.setCaption("Qty Balance");
        xIntegerField1.setName("entity.qty"); // NOI18N
        xIntegerField1.setPreferredSize(new java.awt.Dimension(150, 20));
        xIntegerField1.setRequired(true);
        formPanel1.add(xIntegerField1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XComboBox xComboBox2;
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XIntegerField xIntegerField1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XNumberField xNumberField1;
    // End of variables declaration//GEN-END:variables
    
}
