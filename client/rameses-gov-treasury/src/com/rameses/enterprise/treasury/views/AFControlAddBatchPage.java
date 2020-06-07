/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rameses.enterprise.treasury.views;

import com.rameses.osiris2.themes.OKCancelPage;
import com.rameses.rcp.ui.annotations.StyleSheet;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author Elmo Nazareno
 */
@Template(OKCancelPage.class)
@StyleSheet
public class AFControlAddBatchPage extends javax.swing.JPanel {

    /**
     * Creates new form AFReceiptItemDetailPage
     */
    public AFControlAddBatchPage() {
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

        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xLabel8 = new com.rameses.rcp.control.XLabel();
        xLabel9 = new com.rameses.rcp.control.XLabel();
        xLabel1 = new com.rameses.rcp.control.XLabel();
        xLabel5 = new com.rameses.rcp.control.XLabel();
        xLabel4 = new com.rameses.rcp.control.XLabel();
        xIntegerField1 = new com.rameses.rcp.control.XIntegerField();
        xSeparator1 = new com.rameses.rcp.control.XSeparator();
        xNumberField3 = new com.rameses.rcp.control.XNumberField();
        xNumberField2 = new com.rameses.rcp.control.XNumberField();
        xSeparator2 = new com.rameses.rcp.control.XSeparator();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xTextField4 = new com.rameses.rcp.control.XTextField();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        xSeparator3 = new com.rameses.rcp.control.XSeparator();
        xLookupField1 = new com.rameses.rcp.control.XLookupField();

        formPanel1.setCaptionVAlignment(com.rameses.rcp.constant.UIConstants.CENTER);
        formPanel1.setCaptionWidth(110);

        xLabel8.setCaption("Ref No");
        xLabel8.setExpression("#{ refitem.parent.controlno }");
        com.rameses.rcp.control.border.XLineBorder xLineBorder1 = new com.rameses.rcp.control.border.XLineBorder();
        xLineBorder1.setLineColor(new java.awt.Color(180, 180, 180));
        xLabel8.setBorder(xLineBorder1);
        xLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        xLabel8.setPreferredSize(new java.awt.Dimension(0, 20));
        formPanel1.add(xLabel8);

        xLabel9.setCaption("Ref Type");
        xLabel9.setExpression("#{ refitem.parent.txntype }");
        com.rameses.rcp.control.border.XLineBorder xLineBorder2 = new com.rameses.rcp.control.border.XLineBorder();
        xLineBorder2.setLineColor(new java.awt.Color(180, 180, 180));
        xLabel9.setBorder(xLineBorder2);
        xLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        xLabel9.setPreferredSize(new java.awt.Dimension(0, 20));
        formPanel1.add(xLabel9);

        xLabel1.setCaption("AF No");
        xLabel1.setExpression("#{ refitem.item.objid }");
        com.rameses.rcp.control.border.XLineBorder xLineBorder3 = new com.rameses.rcp.control.border.XLineBorder();
        xLineBorder3.setLineColor(new java.awt.Color(180, 180, 180));
        xLabel1.setBorder(xLineBorder3);
        xLabel1.setPreferredSize(new java.awt.Dimension(0, 20));
        formPanel1.add(xLabel1);

        xLabel5.setCaption("Form Type");
        xLabel5.setExpression("#{ refitem.afunit.formtype }");
        com.rameses.rcp.control.border.XLineBorder xLineBorder4 = new com.rameses.rcp.control.border.XLineBorder();
        xLineBorder4.setLineColor(new java.awt.Color(180, 180, 180));
        xLabel5.setBorder(xLineBorder4);
        xLabel5.setPreferredSize(new java.awt.Dimension(0, 20));
        formPanel1.add(xLabel5);

        xLabel4.setCaption("Unit");
        xLabel4.setExpression("#{ refitem.unit }");
        com.rameses.rcp.control.border.XLineBorder xLineBorder5 = new com.rameses.rcp.control.border.XLineBorder();
        xLineBorder5.setLineColor(new java.awt.Color(180, 180, 180));
        xLabel4.setBorder(xLineBorder5);
        xLabel4.setPreferredSize(new java.awt.Dimension(0, 20));
        formPanel1.add(xLabel4);

        xIntegerField1.setCaption("Unit Qty");
        xIntegerField1.setMinValue(1.0);
        xIntegerField1.setName("entry.qty"); // NOI18N
        xIntegerField1.setPreferredSize(new java.awt.Dimension(150, 20));
        formPanel1.add(xIntegerField1);

        xSeparator1.setPreferredSize(new java.awt.Dimension(0, 20));

        javax.swing.GroupLayout xSeparator1Layout = new javax.swing.GroupLayout(xSeparator1);
        xSeparator1.setLayout(xSeparator1Layout);
        xSeparator1Layout.setHorizontalGroup(
            xSeparator1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );
        xSeparator1Layout.setVerticalGroup(
            xSeparator1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        formPanel1.add(xSeparator1);

        xNumberField3.setCaption("Start Stub No");
        xNumberField3.setDepends(new String[] {"entry.qty"});
        xNumberField3.setName("entry.startstub"); // NOI18N
        xNumberField3.setVisibleWhen("#{ entry.qty != 1 }");
        xNumberField3.setFieldType(Integer.class);
        xNumberField3.setPreferredSize(new java.awt.Dimension(150, 20));
        xNumberField3.setRequired(true);
        formPanel1.add(xNumberField3);

        xNumberField2.setCaption("End Stub No");
        xNumberField2.setName("entry.endstub"); // NOI18N
        xNumberField2.setDepends(new String[] {"entry.qty", "entry.startstub"});
        xNumberField2.setEnabled(false);
        xNumberField2.setFieldType(Integer.class);
        xNumberField2.setPreferredSize(new java.awt.Dimension(150, 20));
        xNumberField2.setVisibleWhen("#{ entry.qty != 1 }");
        formPanel1.add(xNumberField2);

        xSeparator2.setPreferredSize(new java.awt.Dimension(0, 20));

        javax.swing.GroupLayout xSeparator2Layout = new javax.swing.GroupLayout(xSeparator2);
        xSeparator2.setLayout(xSeparator2Layout);
        xSeparator2Layout.setHorizontalGroup(
            xSeparator2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );
        xSeparator2Layout.setVerticalGroup(
            xSeparator2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        formPanel1.add(xSeparator2);

        xTextField3.setCaption("Start Series");
        xTextField3.setName("entry.startseries"); // NOI18N
        xTextField3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xTextField3.setPreferredSize(new java.awt.Dimension(150, 20));
        formPanel1.add(xTextField3);

        xTextField4.setCaption("End Series");
        xTextField4.setName("entry.endseries"); // NOI18N
        xTextField4.setDepends(new String[] {"entry.startseries", "entry.qty"});
        xTextField4.setEnabled(false);
        xTextField4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xTextField4.setPreferredSize(new java.awt.Dimension(150, 20));
        formPanel1.add(xTextField4);

        xTextField1.setCaption("Prefix");
        xTextField1.setName("entry.prefix"); // NOI18N
        xTextField1.setPreferredSize(new java.awt.Dimension(150, 20));
        formPanel1.add(xTextField1);

        xTextField2.setCaption("Suffix");
        xTextField2.setName("entry.suffix"); // NOI18N
        xTextField2.setPreferredSize(new java.awt.Dimension(150, 20));
        formPanel1.add(xTextField2);

        xSeparator3.setPreferredSize(new java.awt.Dimension(0, 20));

        javax.swing.GroupLayout xSeparator3Layout = new javax.swing.GroupLayout(xSeparator3);
        xSeparator3.setLayout(xSeparator3Layout);
        xSeparator3Layout.setHorizontalGroup(
            xSeparator3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );
        xSeparator3Layout.setVerticalGroup(
            xSeparator3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        formPanel1.add(xSeparator3);

        xLookupField1.setCaption("Allocate To");
        xLookupField1.setExpression("#{ entry.allocation.objid }");
        xLookupField1.setHandler("af_allocation:lookup");
        xLookupField1.setName("entry.allocation"); // NOI18N
        xLookupField1.setPreferredSize(new java.awt.Dimension(0, 20));
        formPanel1.add(xLookupField1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private com.rameses.rcp.control.XIntegerField xIntegerField1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLabel xLabel4;
    private com.rameses.rcp.control.XLabel xLabel5;
    private com.rameses.rcp.control.XLabel xLabel8;
    private com.rameses.rcp.control.XLabel xLabel9;
    private com.rameses.rcp.control.XLookupField xLookupField1;
    private com.rameses.rcp.control.XNumberField xNumberField2;
    private com.rameses.rcp.control.XNumberField xNumberField3;
    private com.rameses.rcp.control.XSeparator xSeparator1;
    private com.rameses.rcp.control.XSeparator xSeparator2;
    private com.rameses.rcp.control.XSeparator xSeparator3;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField3;
    private com.rameses.rcp.control.XTextField xTextField4;
    // End of variables declaration//GEN-END:variables
}
