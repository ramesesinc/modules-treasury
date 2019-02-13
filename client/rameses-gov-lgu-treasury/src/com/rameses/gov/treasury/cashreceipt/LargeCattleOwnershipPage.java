/*
 * LargeCattleOwnershipPage.java
 *
 * Created on August 9, 2013, 4:30 PM
 */

package com.rameses.gov.treasury.cashreceipt;

import com.rameses.enterprise.treasury.cashreceipt.SerialCashReceiptPage;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  Elmo
 */
@Template(value=SerialCashReceiptPage.class, target="content")
public class LargeCattleOwnershipPage extends javax.swing.JPanel {
    
    /** Creates new form LargeCattleOwnershipPage */
    public LargeCattleOwnershipPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel7 = new javax.swing.JPanel();
        formPanel3 = new com.rameses.rcp.util.FormPanel();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xNumberField4 = new com.rameses.rcp.control.XNumberField();
        xTextField4 = new com.rameses.rcp.control.XTextField();
        xTextField5 = new com.rameses.rcp.control.XTextField();

        setLayout(new java.awt.BorderLayout());

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Record of Ownership of Large Cattle");
        jPanel7.setBorder(xTitledBorder1);

        formPanel3.setCaption("Record of Ownership");
        formPanel3.setCaptionFont(new java.awt.Font("Arial", 0, 12));
        xTextField3.setCaption("Registered Owner of");
        xTextField3.setCaptionWidth(170);
        xTextField3.setFont(new java.awt.Font("Arial", 0, 12));
        xTextField3.setName("entity.ownerof");
        xTextField3.setPreferredSize(new java.awt.Dimension(0, 20));
        xTextField3.setRequired(true);
        formPanel3.add(xTextField3);

        xComboBox1.setCaption("Sex of Cattle");
        xComboBox1.setCaptionWidth(170);
        xComboBox1.setFont(new java.awt.Font("Arial", 0, 12));
        xComboBox1.setItems("sexList");
        xComboBox1.setName("entity.sex");
        xComboBox1.setPreferredSize(new java.awt.Dimension(130, 22));
        xComboBox1.setRequired(true);
        formPanel3.add(xComboBox1);

        xNumberField4.setCaption("Age of Cattle (Yrs)");
        xNumberField4.setCaptionWidth(170);
        xNumberField4.setFieldType(Integer.class);
        xNumberField4.setFont(new java.awt.Font("Arial", 0, 12));
        xNumberField4.setName("entity.age");
        xNumberField4.setPattern("#,##0");
        xNumberField4.setPreferredSize(new java.awt.Dimension(130, 20));
        xNumberField4.setRequired(true);
        formPanel3.add(xNumberField4);

        xTextField4.setCaption("Brand of Municipality");
        xTextField4.setCaptionWidth(170);
        xTextField4.setFont(new java.awt.Font("Arial", 0, 12));
        xTextField4.setName("entity.municipalitybrand");
        xTextField4.setPreferredSize(new java.awt.Dimension(0, 20));
        formPanel3.add(xTextField4);

        xTextField5.setCaption("Brand of Owner");
        xTextField5.setCaptionWidth(170);
        xTextField5.setFont(new java.awt.Font("Arial", 0, 12));
        xTextField5.setName("entity.ownerbrand");
        xTextField5.setPreferredSize(new java.awt.Dimension(0, 20));
        formPanel3.add(xTextField5);

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 550, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .add(formPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 250, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        add(jPanel7, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel3;
    private javax.swing.JPanel jPanel7;
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XNumberField xNumberField4;
    private com.rameses.rcp.control.XTextField xTextField3;
    private com.rameses.rcp.control.XTextField xTextField4;
    private com.rameses.rcp.control.XTextField xTextField5;
    // End of variables declaration//GEN-END:variables
    
}
