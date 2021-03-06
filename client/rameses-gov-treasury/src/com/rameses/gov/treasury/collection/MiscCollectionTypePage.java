/*
 * MiscCollectionTypePage.java
 *
 * Created on July 9, 2014, 9:21 AM
 */

package com.rameses.gov.treasury.collection;

import com.rameses.rcp.ui.annotations.StyleSheet;

/**
 *
 * @author  arnel
 */
@StyleSheet()
public class MiscCollectionTypePage extends javax.swing.JPanel {
    
    /** Creates new form MiscCollectionTypePage */
    public MiscCollectionTypePage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xLookupField1 = new com.rameses.rcp.control.XLookupField();

        xLookupField1.setCaption("Fund");
        xLookupField1.setCaptionWidth(50);
        xLookupField1.setExpression("#{entity.fund.title}");
        xLookupField1.setHandler("lookupFund");
        xLookupField1.setName("entity.fund");
        xLookupField1.setPreferredSize(new java.awt.Dimension(0, 20));
        formPanel1.add(xLookupField1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private com.rameses.rcp.control.XLookupField xLookupField1;
    // End of variables declaration//GEN-END:variables
    
}
