/*
 * RPTCertificationPage.java
 *
 * Created on July 18, 2011, 11:16 AM
 */
package com.rameses.gov.treasury.certification;

import com.rameses.osiris2.themes.FormPage;
import com.rameses.rcp.ui.annotations.StyleSheet;
import com.rameses.rcp.ui.annotations.Template;
import java.math.BigDecimal;

@Template(FormPage.class)
@StyleSheet
public class CertificationPage extends javax.swing.JPanel {
    
    /** Creates new form RPTCertificationPage */
    public CertificationPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        xTextField8 = new com.rameses.rcp.control.XTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        xTextArea1 = new com.rameses.rcp.control.XTextArea();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xTextField4 = new com.rameses.rcp.control.XTextField();
        xTextField5 = new com.rameses.rcp.control.XTextField();
        xTextField7 = new com.rameses.rcp.control.XTextField();
        xSeparator1 = new com.rameses.rcp.control.XSeparator();
        xTextField6 = new com.rameses.rcp.control.XTextField();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        xNumberField1 = new com.rameses.rcp.control.XNumberField();
        contentPanel = new javax.swing.JPanel();

        jPanel1.setLayout(new java.awt.BorderLayout());

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Certification Detail");
        formPanel1.setBorder(xTitledBorder1);
        xTextField2.setCaption("Requested By");
        xTextField2.setCaptionWidth(135);
        xTextField2.setDepends(new String[] {"entity.taxpayer", "entity.tdno"});
        xTextField2.setName("entity.requestedby");
        xTextField2.setPreferredSize(new java.awt.Dimension(0, 21));
        xTextField2.setRequired(true);
        formPanel1.add(xTextField2);

        xTextField8.setCaption("Address");
        xTextField8.setCaptionWidth(135);
        xTextField8.setDepends(new String[] {"entity.taxpayer", "entity.tdno"});
        xTextField8.setName("entity.requestedbyaddress");
        xTextField8.setPreferredSize(new java.awt.Dimension(0, 21));
        xTextField8.setRequired(true);
        formPanel1.add(xTextField8);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(0, 60));
        xTextArea1.setLineWrap(true);
        xTextArea1.setWrapStyleWord(true);
        xTextArea1.setCaption("Purpose");
        xTextArea1.setCaptionWidth(135);
        xTextArea1.setName("entity.purpose");
        xTextArea1.setPreferredSize(new java.awt.Dimension(120, 60));
        xTextArea1.setRequired(true);
        jScrollPane1.setViewportView(xTextArea1);

        formPanel1.add(jScrollPane1);

        xTextField3.setCaption("<html><u>C</u>ertified By:<font color=\"red\">*</font></html>");
        xTextField3.setCaptionWidth(135);
        xTextField3.setName("entity.certifiedby");
        xTextField3.setPreferredSize(new java.awt.Dimension(0, 21));
        xTextField3.setRequired(true);
        formPanel1.add(xTextField3);

        xTextField4.setCaption("Position/Job Title");
        xTextField4.setCaptionWidth(135);
        xTextField4.setName("entity.certifiedbytitle");
        xTextField4.setPreferredSize(new java.awt.Dimension(0, 21));
        xTextField4.setRequired(true);
        formPanel1.add(xTextField4);

        xTextField5.setCaption("Authority of");
        xTextField5.setCaptionWidth(135);
        xTextField5.setName("entity.byauthority");
        xTextField5.setPreferredSize(new java.awt.Dimension(0, 21));
        formPanel1.add(xTextField5);

        xTextField7.setCaption("Position/Job Title");
        xTextField7.setCaptionWidth(135);
        xTextField7.setName("entity.byauthoritytitle");
        xTextField7.setPreferredSize(new java.awt.Dimension(0, 21));
        formPanel1.add(xTextField7);

        xSeparator1.setCellPadding(new java.awt.Insets(5, 0, 0, 0));
        xSeparator1.setPreferredSize(new java.awt.Dimension(0, 20));
        org.jdesktop.layout.GroupLayout xSeparator1Layout = new org.jdesktop.layout.GroupLayout(xSeparator1);
        xSeparator1.setLayout(xSeparator1Layout);
        xSeparator1Layout.setHorizontalGroup(
            xSeparator1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 510, Short.MAX_VALUE)
        );
        xSeparator1Layout.setVerticalGroup(
            xSeparator1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 20, Short.MAX_VALUE)
        );
        formPanel1.add(xSeparator1);

        xTextField6.setCaption("O.R. No.");
        xTextField6.setCaptionWidth(117);
        xTextField6.setCellPadding(new java.awt.Insets(0, 20, 0, 0));
        xTextField6.setDepends(new String[] {"officialuse"});
        xTextField6.setName("entity.orno");
        xTextField6.setPreferredSize(new java.awt.Dimension(150, 21));
        xTextField6.setRequired(true);
        formPanel1.add(xTextField6);

        xDateField1.setCaption("O.R. Date");
        xDateField1.setCaptionWidth(117);
        xDateField1.setCellPadding(new java.awt.Insets(0, 20, 0, 0));
        xDateField1.setDepends(new String[] {"officialuse"});
        xDateField1.setName("entity.ordate");
        xDateField1.setPreferredSize(new java.awt.Dimension(150, 21));
        xDateField1.setRequired(true);
        formPanel1.add(xDateField1);

        xNumberField1.setCaption("O.R. Amount");
        xNumberField1.setCaptionWidth(117);
        xNumberField1.setCellPadding(new java.awt.Insets(0, 20, 0, 0));
        xNumberField1.setDepends(new String[] {"officialuse"});
        xNumberField1.setFieldType(BigDecimal.class);
        xNumberField1.setName("entity.oramount");
        xNumberField1.setPattern("#,##0.00");
        xNumberField1.setPreferredSize(new java.awt.Dimension(150, 21));
        formPanel1.add(xNumberField1);

        jPanel1.add(formPanel1, java.awt.BorderLayout.CENTER);

        contentPanel.setLayout(new java.awt.BorderLayout());

        contentPanel.setBackground(new java.awt.Color(153, 153, 153));
        contentPanel.setName("displaypage");
        jPanel1.add(contentPanel, java.awt.BorderLayout.NORTH);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 524, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private com.rameses.rcp.util.FormPanel formPanel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XNumberField xNumberField1;
    private com.rameses.rcp.control.XSeparator xSeparator1;
    private com.rameses.rcp.control.XTextArea xTextArea1;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField3;
    private com.rameses.rcp.control.XTextField xTextField4;
    private com.rameses.rcp.control.XTextField xTextField5;
    private com.rameses.rcp.control.XTextField xTextField6;
    private com.rameses.rcp.control.XTextField xTextField7;
    private com.rameses.rcp.control.XTextField xTextField8;
    // End of variables declaration//GEN-END:variables
    
}
