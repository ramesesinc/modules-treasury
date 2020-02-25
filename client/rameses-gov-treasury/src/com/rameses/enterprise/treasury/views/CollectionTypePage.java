package com.rameses.enterprise.treasury.views;


import com.rameses.rcp.ui.annotations.Template;
import com.rameses.seti2.views.CrudFormPage;

@Template(CrudFormPage.class)
public class CollectionTypePage extends javax.swing.JPanel {
    
    public CollectionTypePage() {
        initComponents();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        xTabbedPane1 = new com.rameses.rcp.control.XTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xIntegerField1 = new com.rameses.rcp.control.XIntegerField();
        xComboBox3 = new com.rameses.rcp.control.XComboBox();
        xSuggest1 = new com.rameses.rcp.control.XSuggest();
        xLabel2 = new com.rameses.rcp.control.XLabel();
        xLookupField2 = new com.rameses.rcp.control.XLookupField();
        jPanel4 = new javax.swing.JPanel();
        xFormPanel2 = new com.rameses.rcp.control.XFormPanel();
        xCheckBox3 = new com.rameses.rcp.control.XCheckBox();
        xCheckBox2 = new com.rameses.rcp.control.XCheckBox();
        xCheckBox1 = new com.rameses.rcp.control.XCheckBox();
        xCheckBox4 = new com.rameses.rcp.control.XCheckBox();
        xCheckBox5 = new com.rameses.rcp.control.XCheckBox();
        xCheckBox6 = new com.rameses.rcp.control.XCheckBox();
        xFormPanel3 = new com.rameses.rcp.control.XFormPanel();
        xCheckBox7 = new com.rameses.rcp.control.XCheckBox();
        xIntegerField2 = new com.rameses.rcp.control.XIntegerField();
        xPanel1 = new com.rameses.rcp.control.XPanel();
        schemaList1 = new com.rameses.seti2.components.SchemaList();
        xPanel2 = new com.rameses.rcp.control.XPanel();
        schemaList2 = new com.rameses.seti2.components.SchemaList();
        jPanel1 = new javax.swing.JPanel();
        xButton1 = new com.rameses.rcp.control.XButton();

        xTabbedPane1.setItems("sections");
        xTabbedPane1.setDynamic(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 10, 10, 10));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        xFormPanel1.setCaptionVAlignment(com.rameses.rcp.constant.UIConstants.CENTER);
        xFormPanel1.setCaptionWidth(150);

        xTextField1.setCaption("Name");
        xTextField1.setName("entity.name"); // NOI18N
        xTextField1.setPreferredSize(new java.awt.Dimension(0, 20));
        xTextField1.setRequired(true);
        xTextField1.setSpaceChar('_');
        xFormPanel1.add(xTextField1);

        xTextField2.setCaption("Title");
        xTextField2.setName("entity.title"); // NOI18N
        xTextField2.setPreferredSize(new java.awt.Dimension(0, 20));
        xTextField2.setRequired(true);
        xTextField2.setTextCase(com.rameses.rcp.constant.TextCase.NONE);
        xFormPanel1.add(xTextField2);

        xComboBox1.setCaption("Form name");
        xComboBox1.setItems("formTypes");
        xComboBox1.setName("entity.formno"); // NOI18N
        xComboBox1.setPreferredSize(new java.awt.Dimension(0, 22));
        xComboBox1.setRequired(true);
        xFormPanel1.add(xComboBox1);

        xTextField3.setCaption("Barcode Key");
        xTextField3.setName("entity.barcodekey"); // NOI18N
        xTextField3.setCellPadding(new java.awt.Insets(2, 0, 0, 0));
        xFormPanel1.add(xTextField3);

        xIntegerField1.setCaption("Sort Order");
        xIntegerField1.setName("entity.sortorder"); // NOI18N
        xFormPanel1.add(xIntegerField1);

        xComboBox3.setCaption("GUI Screen Handler");
        xComboBox3.setDepends(new String[] {"selectedForm"});
        xComboBox3.setItems("handlers");
        xComboBox3.setName("entity.handler"); // NOI18N
        xComboBox3.setDynamic(true);
        xComboBox3.setPreferredSize(new java.awt.Dimension(0, 22));
        xComboBox3.setRequired(true);
        xFormPanel1.add(xComboBox3);

        xSuggest1.setCaption("Category");
        xSuggest1.setExpression("#{item.category}");
        xSuggest1.setHandler("categoryModel");
        xSuggest1.setItemExpression("#{item.category}");
        xSuggest1.setName("entity.category"); // NOI18N
        xSuggest1.setPreferredSize(new java.awt.Dimension(0, 22));
        xSuggest1.setTextCase(com.rameses.rcp.constant.TextCase.UPPER);
        xFormPanel1.add(xSuggest1);

        xLabel2.setCellPadding(new java.awt.Insets(20, 0, 10, 0));
        xLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        xLabel2.setPadding(new java.awt.Insets(1, 0, 1, 1));
        xLabel2.setShowCaption(false);
        xLabel2.setText("Specify if this collection type is applicable only to the ff. fund and org:");
        xFormPanel1.add(xLabel2);

        xLookupField2.setCaption("          Fund");
        xLookupField2.setExpression("#{item.code} #{item.title}");
        xLookupField2.setHandler("fund:lookup");
        xLookupField2.setName("entity.fund"); // NOI18N
        xLookupField2.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xLookupField2);

        jPanel2.add(xFormPanel1, java.awt.BorderLayout.WEST);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 0, 0));
        com.rameses.rcp.control.layout.YLayout yLayout1 = new com.rameses.rcp.control.layout.YLayout();
        yLayout1.setSpacing(10);
        jPanel4.setLayout(yLayout1);

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setPadding(new java.awt.Insets(25, 20, 20, 25));
        xTitledBorder1.setTitle("Display Options");
        xFormPanel2.setBorder(xTitledBorder1);
        xFormPanel2.setShowCaption(false);

        xCheckBox3.setCaption("");
        xCheckBox3.setCheckValue(1);
        xCheckBox3.setName("entity.allowonline"); // NOI18N
        xCheckBox3.setUncheckValue(0);
        xCheckBox3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        xCheckBox3.setCellPadding(new java.awt.Insets(10, 0, 0, 0));
        xCheckBox3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xCheckBox3.setShowCaption(false);
        xCheckBox3.setText("Show in Online Mode");
        xCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xCheckBox3ActionPerformed(evt);
            }
        });
        xFormPanel2.add(xCheckBox3);

        xCheckBox2.setCaption("");
        xCheckBox2.setCheckValue(1);
        xCheckBox2.setName("entity.allowoffline"); // NOI18N
        xCheckBox2.setUncheckValue(0);
        xCheckBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        xCheckBox2.setCellPadding(new java.awt.Insets(2, 0, 0, 0));
        xCheckBox2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xCheckBox2.setShowCaption(false);
        xCheckBox2.setText("Show in Offline Mode");
        xCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xCheckBox2ActionPerformed(evt);
            }
        });
        xFormPanel2.add(xCheckBox2);

        xCheckBox1.setCaption("");
        xCheckBox1.setCheckValue(1);
        xCheckBox1.setName("entity.allowbatch"); // NOI18N
        xCheckBox1.setUncheckValue(0);
        xCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        xCheckBox1.setCellPadding(new java.awt.Insets(2, 0, 0, 0));
        xCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xCheckBox1.setShowCaption(false);
        xCheckBox1.setText("Show in Batch Capture Mode");
        xFormPanel2.add(xCheckBox1);

        xCheckBox4.setCaption("");
        xCheckBox4.setCheckValue(1);
        xCheckBox4.setName("entity.allowpaymentorder"); // NOI18N
        xCheckBox4.setUncheckValue(0);
        xCheckBox4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        xCheckBox4.setCellPadding(new java.awt.Insets(2, 0, 0, 0));
        xCheckBox4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xCheckBox4.setShowCaption(false);
        xCheckBox4.setText("Show in Payment Order");
        xFormPanel2.add(xCheckBox4);

        xCheckBox5.setCaption("");
        xCheckBox5.setCheckValue(1);
        xCheckBox5.setName("entity.allowkiosk"); // NOI18N
        xCheckBox5.setUncheckValue(0);
        xCheckBox5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        xCheckBox5.setCellPadding(new java.awt.Insets(2, 0, 0, 0));
        xCheckBox5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xCheckBox5.setShowCaption(false);
        xCheckBox5.setText("Show in Kiosk");
        xFormPanel2.add(xCheckBox5);

        xCheckBox6.setCaption("");
        xCheckBox6.setCheckValue(1);
        xCheckBox6.setName("entity.allowcreditmemo"); // NOI18N
        xCheckBox6.setUncheckValue(0);
        xCheckBox6.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        xCheckBox6.setCellPadding(new java.awt.Insets(2, 0, 0, 0));
        xCheckBox6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xCheckBox6.setShowCaption(false);
        xCheckBox6.setText("Show in Credit Memo");
        xFormPanel2.add(xCheckBox6);

        jPanel4.add(xFormPanel2);

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder2 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder2.setPadding(new java.awt.Insets(25, 20, 20, 25));
        xTitledBorder2.setTitle("Other Options");
        xFormPanel3.setBorder(xTitledBorder2);
        xFormPanel3.setCaptionWidth(120);
        xFormPanel3.setShowCaption(false);

        xCheckBox7.setCaption("Multi Receipt");
        xCheckBox7.setCheckValue(1);
        xCheckBox7.setDepends(new String[] {"entity.handler"});
        xCheckBox7.setDisableWhen("1==1");
        xCheckBox7.setName("multireceipt"); // NOI18N
        xCheckBox7.setUncheckValue(0);
        xCheckBox7.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        xCheckBox7.setCellPadding(new java.awt.Insets(10, 0, 0, 0));
        xCheckBox7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xCheckBox7ActionPerformed(evt);
            }
        });
        xFormPanel3.add(xCheckBox7);

        xIntegerField2.setCaption("Items Per Page");
        xIntegerField2.setDepends(new String[] {"entity.handler"});
        xIntegerField2.setDisableWhen("allowMultiReceiptOption != true");
        xIntegerField2.setName("entity.info.itemsperpage"); // NOI18N
        xIntegerField2.setPreferredSize(new java.awt.Dimension(60, 20));
        xFormPanel3.add(xIntegerField2);

        jPanel4.add(xFormPanel3);

        jPanel2.add(jPanel4, java.awt.BorderLayout.EAST);

        jPanel3.add(jPanel2, java.awt.BorderLayout.WEST);

        xTabbedPane1.addTab("  General Information   ", jPanel3);

        xPanel1.setVisibleWhen("#{ mode != 'create' }");

        schemaList1.setColumns(new com.rameses.rcp.common.Column[]{
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "account.title"}
                , new Object[]{"caption", "Account Title"}
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
                new Object[]{"name", "tag"}
                , new Object[]{"caption", "Tag"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 150}
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
                new Object[]{"name", "valuetype"}
                , new Object[]{"caption", "Value Type"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
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
                new Object[]{"name", "defaultvalue"}
                , new Object[]{"caption", "Default Value"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
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
                new Object[]{"name", "sortorder"}
                , new Object[]{"caption", "Sort Order"}
                , new Object[]{"width", 100}
                , new Object[]{"minWidth", 0}
                , new Object[]{"maxWidth", 100}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", false}
                , new Object[]{"visible", true}
                , new Object[]{"visibleWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.IntegerColumnHandler(null, -1, -1)}
            })
        });
        schemaList1.setCustomFilter("collectiontypeid = :objid");
        schemaList1.setOrderBy("sortorder");
        schemaList1.setQueryName("entity");
        schemaList1.setSchemaName("collectiontype_account");
        schemaList1.setAllowCreate(true);
        schemaList1.setAllowDelete(true);

        org.jdesktop.layout.GroupLayout xPanel1Layout = new org.jdesktop.layout.GroupLayout(xPanel1);
        xPanel1.setLayout(xPanel1Layout);
        xPanel1Layout.setHorizontalGroup(
            xPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(xPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(schemaList1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                .addContainerGap())
        );
        xPanel1Layout.setVerticalGroup(
            xPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(xPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(schemaList1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addContainerGap())
        );

        xTabbedPane1.addTab("  Accounts     ", xPanel1);

        xPanel2.setVisibleWhen("#{ mode != 'create' }");
        xPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        xPanel2.setLayout(new java.awt.BorderLayout());

        schemaList2.setColumns(new com.rameses.rcp.common.Column[]{
            new com.rameses.rcp.common.Column(new Object[]{
                new Object[]{"name", "org.objid"}
                , new Object[]{"caption", "Org Code"}
                , new Object[]{"width", 200}
                , new Object[]{"minWidth", 250}
                , new Object[]{"maxWidth", 300}
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
                new Object[]{"name", "org.name"}
                , new Object[]{"caption", "Org Name"}
                , new Object[]{"width", 100}
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
                new Object[]{"name", "org.type"}
                , new Object[]{"caption", "Org Type"}
                , new Object[]{"width", 150}
                , new Object[]{"minWidth", 150}
                , new Object[]{"maxWidth", 200}
                , new Object[]{"required", false}
                , new Object[]{"resizable", true}
                , new Object[]{"nullWhenEmpty", true}
                , new Object[]{"editable", false}
                , new Object[]{"visible", true}
                , new Object[]{"visibleWhen", null}
                , new Object[]{"textCase", com.rameses.rcp.constant.TextCase.NONE}
                , new Object[]{"typeHandler", new com.rameses.rcp.common.TextColumnHandler()}
            })
        });
        schemaList2.setCustomFilter("collectiontypeid = :objid");
        schemaList2.setHandlerName("orgListHandler");
        schemaList2.setQueryName("entity");
        schemaList2.setSchemaName("collectiontype_org");
        schemaList2.setAllowDelete(true);
        xPanel2.add(schemaList2, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        jPanel1.setLayout(new com.rameses.rcp.control.layout.XLayout());

        xButton1.setName("addOrg"); // NOI18N
        xButton1.setText("Add Org");
        jPanel1.add(xButton1);

        xPanel2.add(jPanel1, java.awt.BorderLayout.SOUTH);

        xTabbedPane1.addTab("  Orgs      ", xPanel2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(xTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(xTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void xCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xCheckBox3ActionPerformed

    private void xCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xCheckBox2ActionPerformed

    private void xCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xCheckBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xCheckBox7ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private com.rameses.seti2.components.SchemaList schemaList1;
    private com.rameses.seti2.components.SchemaList schemaList2;
    private com.rameses.rcp.control.XButton xButton1;
    private com.rameses.rcp.control.XCheckBox xCheckBox1;
    private com.rameses.rcp.control.XCheckBox xCheckBox2;
    private com.rameses.rcp.control.XCheckBox xCheckBox3;
    private com.rameses.rcp.control.XCheckBox xCheckBox4;
    private com.rameses.rcp.control.XCheckBox xCheckBox5;
    private com.rameses.rcp.control.XCheckBox xCheckBox6;
    private com.rameses.rcp.control.XCheckBox xCheckBox7;
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XComboBox xComboBox3;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XFormPanel xFormPanel2;
    private com.rameses.rcp.control.XFormPanel xFormPanel3;
    private com.rameses.rcp.control.XIntegerField xIntegerField1;
    private com.rameses.rcp.control.XIntegerField xIntegerField2;
    private com.rameses.rcp.control.XLabel xLabel2;
    private com.rameses.rcp.control.XLookupField xLookupField2;
    private com.rameses.rcp.control.XPanel xPanel1;
    private com.rameses.rcp.control.XPanel xPanel2;
    private com.rameses.rcp.control.XSuggest xSuggest1;
    private com.rameses.rcp.control.XTabbedPane xTabbedPane1;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField3;
    // End of variables declaration//GEN-END:variables
    
}
