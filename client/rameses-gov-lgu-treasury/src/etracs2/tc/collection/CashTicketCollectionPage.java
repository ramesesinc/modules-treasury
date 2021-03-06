/*
 * GeneralCollectionPage.java
 *
 * Created on April 20, 2011, 11:00 AM
 */

package etracs2.tc.collection;

import com.rameses.rcp.ui.annotations.StyleSheet;
import java.math.BigDecimal;

/**
 *
 * @author  jzamora
 */
@StyleSheet("etracs2/tc/collection/CollectionPage.style")
public class CashTicketCollectionPage extends javax.swing.JPanel {
    
    /** Creates new form GeneralCollectionPage */
    public CashTicketCollectionPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel3 = new javax.swing.JPanel();
        xLabel9 = new com.rameses.rcp.control.XLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        xSubFormPanel1 = new com.rameses.rcp.control.XSubFormPanel();
        jPanel6 = new javax.swing.JPanel();
        xDataTable1 = new com.rameses.rcp.control.XDataTable();
        xLabel1 = new com.rameses.rcp.control.XLabel();
        jPanel7 = new javax.swing.JPanel();
        formPanel3 = new com.rameses.rcp.util.FormPanel();
        xLabel3 = new com.rameses.rcp.control.XLabel();
        xNumberField4 = new com.rameses.rcp.control.XNumberField();
        xLabel2 = new com.rameses.rcp.control.XLabel();

        setLayout(new java.awt.BorderLayout());

        setPreferredSize(new java.awt.Dimension(884, 487));
        jPanel3.setLayout(new java.awt.BorderLayout());

        xLabel9.setBackground(new java.awt.Color(1, 47, 8));
        xLabel9.setFont(new java.awt.Font("Arial", 1, 18));
        xLabel9.setForeground(new java.awt.Color(255, 255, 255));
        xLabel9.setName("collectionTitle");
        xLabel9.setOpaque(true);
        jPanel3.add(xLabel9, java.awt.BorderLayout.SOUTH);

        add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(230, 100));
        add(jPanel1, java.awt.BorderLayout.EAST);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        xSubFormPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        xSubFormPanel1.setHandler("paymentSummary");
        xSubFormPanel1.setPreferredSize(new java.awt.Dimension(40, 120));
        jPanel5.add(xSubFormPanel1, java.awt.BorderLayout.SOUTH);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel6.setPreferredSize(new java.awt.Dimension(772, 487));
        xDataTable1.setHandler("listHandler");
        xDataTable1.setImmediate(true);
        xDataTable1.setIndex(-1);
        xDataTable1.setName("selectedItem");
        xDataTable1.setRowHeight(20);
        xDataTable1.setVarStatus("listItem");
        jPanel6.add(xDataTable1, java.awt.BorderLayout.CENTER);

        xLabel1.setForeground(new java.awt.Color(204, 0, 0));
        xLabel1.setDepends(new String[] {"selectedItem"});
        xLabel1.setFont(new java.awt.Font("Arial", 1, 11));
        xLabel1.setName("message");
        xLabel1.setPreferredSize(new java.awt.Dimension(42, 20));
        jPanel6.add(xLabel1, java.awt.BorderLayout.SOUTH);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel7.setPreferredSize(new java.awt.Dimension(100, 110));

        formPanel3.setCaptionBorder(null);
        formPanel3.setCaptionFont(new java.awt.Font("Arial", 0, 14));
        formPanel3.setPadding(new java.awt.Insets(15, 15, 5, 5));
        formPanel3.setPreferredSize(new java.awt.Dimension(275, 80));
        xLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        xLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel3.setCaption("Current Balance");
        xLabel3.setCaptionWidth(130);
        xLabel3.setDepends(new String[] {"qtyissued"});
        xLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
        xLabel3.setName("balance");
        xLabel3.setPreferredSize(new java.awt.Dimension(150, 24));
        formPanel3.add(xLabel3);

        xNumberField4.setCaption("Quantity Issued");
        xNumberField4.setCaptionWidth(130);
        xNumberField4.setFont(new java.awt.Font("Tahoma", 1, 14));
        xNumberField4.setName("qtyissued");
        xNumberField4.setPreferredSize(new java.awt.Dimension(150, 24));
        xNumberField4.setRequired(true);
        formPanel3.add(xNumberField4);

        xLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        xLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel2.setCaption("Amount Collected");
        xLabel2.setCaptionWidth(130);
        xLabel2.setDepends(new String[] {"qtyissued"});
        xLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        xLabel2.setName("amount");
        xLabel2.setPreferredSize(new java.awt.Dimension(150, 24));
        formPanel3.add(xLabel2);

        jPanel7.add(formPanel3, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel5.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel5, java.awt.BorderLayout.CENTER);

        add(jPanel4, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private com.rameses.rcp.control.XDataTable xDataTable1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLabel xLabel2;
    private com.rameses.rcp.control.XLabel xLabel3;
    private com.rameses.rcp.control.XLabel xLabel9;
    private com.rameses.rcp.control.XNumberField xNumberField4;
    private com.rameses.rcp.control.XSubFormPanel xSubFormPanel1;
    // End of variables declaration//GEN-END:variables
    
}
