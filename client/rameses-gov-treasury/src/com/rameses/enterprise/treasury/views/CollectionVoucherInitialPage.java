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
public class CollectionVoucherInitialPage extends javax.swing.JPanel {

    /**
     * Creates new form LiquidationInitialPage
     */
    public CollectionVoucherInitialPage() {
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xDataTable1 = new com.rameses.rcp.control.XDataTable();
        jPanel2 = new javax.swing.JPanel();
        xButton2 = new com.rameses.rcp.control.XButton();
        xFormPanel2 = new com.rameses.rcp.control.XFormPanel();
        xLabel1 = new com.rameses.rcp.control.XLabel();
        xLabel2 = new com.rameses.rcp.control.XLabel();

        setPreferredSize(new java.awt.Dimension(854, 470));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new com.rameses.rcp.control.layout.XLayout());

        xFormPanel1.setCaptionVAlignment(com.rameses.rcp.constant.UIConstants.CENTER);
        xFormPanel1.setPadding(new java.awt.Insets(0, 0, 10, 0));

        xComboBox1.setCaption("Select Remittance Date");
        xComboBox1.setItems("datesList");
        xComboBox1.setName("controldate"); // NOI18N
        xComboBox1.setAllowNull(false);
        xComboBox1.setCaptionWidth(160);
        xComboBox1.setDynamic(true);
        xComboBox1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        xComboBox1.setPreferredSize(new java.awt.Dimension(150, 22));
        xFormPanel1.add(xComboBox1);

        jPanel1.add(xFormPanel1);

        jPanel3.add(jPanel1, java.awt.BorderLayout.NORTH);

        xDataTable1.setHandler("remittanceListHandler");
        xDataTable1.setName("selectedRemittance"); // NOI18N
        xDataTable1.setColumns(new com.rameses.rcp.common.Column[]{
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "posted"}
                , new Object[]{"caption", "Accepted"}
                , new Object[]{"width", 70}
                , new Object[]{"minWidth", 70}
                , new Object[]{"maxWidth", 100}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", false}
                , new Object[]{"visible", true}
                , new Object[]{"visibleWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.CheckBoxColumnHandler(java.lang.Integer.class, 1, 0)}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "controldate"}
                , new Object[]{"caption", "Control Date"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 10}
                , new Object[]{"maxWidth", 100}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", false}
                , new Object[]{"visible", true}
                , new Object[]{"visibleWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.TextColumnHandler()}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "controlno"}
                , new Object[]{"caption", "Control No"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 10}
                , new Object[]{"maxWidth", 200}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", false}
                , new Object[]{"visible", true}
                , new Object[]{"visibleWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.TextColumnHandler()}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "collector.name"}
                , new Object[]{"caption", "Collector"}
                , new Object[]{"width", 200}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 0}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", false}
                , new Object[]{"visible", true}
                , new Object[]{"visibleWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.TextColumnHandler()}
            }),
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "amount"}
                , new Object[]{"caption", "Amount"}
                , new Object[]{"width", 120}
                , new Object[]{"minWidth", 50}
                , new Object[]{"maxWidth", 150}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", false}
                , new Object[]{"visible", true}
                , new Object[]{"visibleWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.DecimalColumnHandler("#,##0.00", -1.0, -1.0, false, 2)}
            })
        });
        xDataTable1.setDynamic(true);
        jPanel3.add(xDataTable1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        jPanel2.setLayout(new java.awt.BorderLayout());

        xButton2.setDepends(new String[] {"controldate", "selectedRemittance"});
        xButton2.setDisableWhen("#{selectedRemittance == null}");
        xButton2.setMnemonic('v');
        xButton2.setName("viewRemittance"); // NOI18N
        xButton2.setText("View");
        jPanel2.add(xButton2, java.awt.BorderLayout.WEST);

        xFormPanel2.setCaptionVAlignment(com.rameses.rcp.constant.UIConstants.CENTER);
        xFormPanel2.setOrientation(com.rameses.rcp.constant.UIConstants.HORIZONTAL);
        xFormPanel2.setPadding(new java.awt.Insets(0, 0, 0, 0));

        xLabel1.setCaption("Remaining Balance");
        xLabel1.setDepends(new String[] {"controldate", "totals"});
        xLabel1.setExpression("#{remBalance}");
        xLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180)));
        xLabel1.setCaptionFontStyle("font-size: 12;");
        xLabel1.setCaptionWidth(130);
        xLabel1.setCellPadding(new java.awt.Insets(0, 0, 0, 20));
        xLabel1.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        xLabel1.setNumberFormat("#,##0.00");
        xLabel1.setPreferredSize(new java.awt.Dimension(160, 22));
        xFormPanel2.add(xLabel1);

        xLabel2.setCaption("Amount To Liquidate");
        xLabel2.setDepends(new String[] {"controldate", "totals"});
        xLabel2.setExpression("#{totalAmount}");
        xLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180)));
        xLabel2.setCaptionFontStyle("font-size: 12;");
        xLabel2.setCaptionWidth(140);
        xLabel2.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        xLabel2.setNumberFormat("#,##0.00");
        xLabel2.setPreferredSize(new java.awt.Dimension(160, 22));
        xFormPanel2.add(xLabel2);

        jPanel2.add(xFormPanel2, java.awt.BorderLayout.EAST);

        jPanel3.add(jPanel2, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private com.rameses.rcp.control.XButton xButton2;
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XDataTable xDataTable1;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XFormPanel xFormPanel2;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLabel xLabel2;
    // End of variables declaration//GEN-END:variables
}
