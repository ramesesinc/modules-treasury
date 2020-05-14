/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rameses.gov.treasury.depositvoucher.report.rcd;

import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author wflores
 */
@Template(value=com.rameses.gov.treasury.report.views.PreviewByFundPage.class, target="filter")
public class PreviewByFundPage extends javax.swing.JPanel {

    /**
     * Creates new form PreviewByFundPage
     */
    public PreviewByFundPage() {
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

        xLabel1 = new com.rameses.rcp.control.XLabel();
        xComboBox2 = new com.rameses.rcp.control.XComboBox();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));
        com.rameses.rcp.control.layout.XLayout xLayout1 = new com.rameses.rcp.control.layout.XLayout();
        xLayout1.setSpacing(10);
        setLayout(xLayout1);

        xLabel1.setText("Template : ");
        add(xLabel1);

        xComboBox2.setCaption("Template");
        xComboBox2.setExpression("#{item.title}");
        xComboBox2.setItems("templates");
        xComboBox2.setName("template"); // NOI18N
        xComboBox2.setAllowNull(false);
        xComboBox2.setCaptionWidth(70);
        xComboBox2.setDynamic(true);
        add(xComboBox2);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XComboBox xComboBox2;
    private com.rameses.rcp.control.XLabel xLabel1;
    // End of variables declaration//GEN-END:variables
}
