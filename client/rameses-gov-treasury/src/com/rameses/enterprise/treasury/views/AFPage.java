/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rameses.enterprise.treasury.views;

import com.rameses.rcp.ui.annotations.StyleSheet;
import com.rameses.rcp.ui.annotations.Template;
import com.rameses.seti2.views.CrudFormPage;

/**
 *
 * @author Elmo Nazareno
 */
@Template(CrudFormPage.class)
@StyleSheet
public class AFPage extends javax.swing.JPanel {

    /**
     * Creates new form AFPage
     */
    public AFPage() {
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
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xComboBox2 = new com.rameses.rcp.control.XComboBox();
        xDecimalField1 = new com.rameses.rcp.control.XDecimalField();
        xCheckBox1 = new com.rameses.rcp.control.XCheckBox();
        xIntegerField1 = new com.rameses.rcp.control.XIntegerField();

        xFormPanel1.setCaptionWidth(150);

        xTextField1.setCaption("Name");
        xTextField1.setName("entity.objid"); // NOI18N
        xTextField1.setRequired(true);
        xTextField1.setSpaceChar('_');
        xFormPanel1.add(xTextField1);

        xTextField2.setCaption("Title");
        xTextField2.setName("entity.title"); // NOI18N
        xTextField2.setPreferredSize(new java.awt.Dimension(0, 20));
        xTextField2.setRequired(true);
        xFormPanel1.add(xTextField2);

        xComboBox1.setCaption("Use Type");
        xComboBox1.setItems("useTypes");
        xComboBox1.setName("entity.usetype"); // NOI18N
        xComboBox1.setPreferredSize(new java.awt.Dimension(0, 20));
        xComboBox1.setRequired(true);
        xFormPanel1.add(xComboBox1);

        xComboBox2.setCaption("Form Type");
        xComboBox2.setItems("formTypes");
        xComboBox2.setName("entity.formtype"); // NOI18N
        xComboBox2.setPreferredSize(new java.awt.Dimension(0, 20));
        xComboBox2.setRequired(true);
        xFormPanel1.add(xComboBox2);

        xDecimalField1.setCaption("Denomination");
        xDecimalField1.setDepends(new String[] {"entity.formtype"});
        xDecimalField1.setName("entity.denomination"); // NOI18N
        xDecimalField1.setRequired(true);
        xDecimalField1.setVisibleWhen("#{ entity.formtype == 'cashticket' }");
        xFormPanel1.add(xDecimalField1);

        xCheckBox1.setCaption("");
        xCheckBox1.setDepends(new String[] {"entity.formtype"});
        xCheckBox1.setDisableWhen("#{ mode == 'read' }");
        xCheckBox1.setName("serial"); // NOI18N
        xCheckBox1.setVisibleWhen("#{ entity.formtype == 'cashticket' }");
        xCheckBox1.setText("Serial");
        xFormPanel1.add(xCheckBox1);

        xIntegerField1.setCaption("Series Length");
        xIntegerField1.setDepends(new String[] {"entity.formtype", "serial"});
        xIntegerField1.setName("entity.serieslength"); // NOI18N
        xIntegerField1.setVisibleWhen("#{ entity.formtype != 'cashticket' || serial == true }");
        xIntegerField1.setRequired(true);
        xFormPanel1.add(xIntegerField1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xFormPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xFormPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XCheckBox xCheckBox1;
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XComboBox xComboBox2;
    private com.rameses.rcp.control.XDecimalField xDecimalField1;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XIntegerField xIntegerField1;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField2;
    // End of variables declaration//GEN-END:variables
}
