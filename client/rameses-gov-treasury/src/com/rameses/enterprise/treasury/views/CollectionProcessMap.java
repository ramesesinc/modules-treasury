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
public class CollectionProcessMap extends javax.swing.JPanel {

    /**
     * Creates new form CollectionQuickView
     */
    public CollectionProcessMap() {
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        xButton7 = new com.rameses.rcp.control.XButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        xButton17 = new com.rameses.rcp.control.XButton();
        jLabel33 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        xButton1 = new com.rameses.rcp.control.XButton();
        xButton12 = new com.rameses.rcp.control.XButton();
        xButton2 = new com.rameses.rcp.control.XButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        xButton9 = new com.rameses.rcp.control.XButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        xButton11 = new com.rameses.rcp.control.XButton();
        jLabel13 = new javax.swing.JLabel();
        xButton5 = new com.rameses.rcp.control.XButton();
        xButton8 = new com.rameses.rcp.control.XButton();
        jLabel11 = new javax.swing.JLabel();
        xButton14 = new com.rameses.rcp.control.XButton();
        jLabel25 = new javax.swing.JLabel();
        xButton18 = new com.rameses.rcp.control.XButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        xButton3 = new com.rameses.rcp.control.XButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        xButton10 = new com.rameses.rcp.control.XButton();
        xButton13 = new com.rameses.rcp.control.XButton();
        xButton6 = new com.rameses.rcp.control.XButton();
        jPanel7 = new javax.swing.JPanel();
        xButton15 = new com.rameses.rcp.control.XButton();
        xButton16 = new com.rameses.rcp.control.XButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(null);

        xButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/postledger.png"))); // NOI18N
        xButton7.setBorderPainted(false);
        xButton7.setCaption("Issue Online");
        xButton7.setContentAreaFilled(false);
        xButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton7.setName("postDailyCollection"); // NOI18N
        xButton7.setText("<html><center>Daily Collection</center></html>");
        xButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(xButton7);
        xButton7.setBounds(150, 40, 90, 100);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("ACCOUNTING");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(0, 0, 220, 14);

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("<html>&#8595;</html>");
        jPanel2.add(jLabel31);
        jLabel31.setBounds(390, 0, 40, 30);

        xButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/postledger.png"))); // NOI18N
        xButton17.setBorderPainted(false);
        xButton17.setCaption("Issue Online");
        xButton17.setContentAreaFilled(false);
        xButton17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton17.setText("<html><center>Post Deposit</center></html>");
        xButton17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(xButton17);
        xButton17.setBounds(370, 40, 90, 100);

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("<html>&#8595;</html>");
        jPanel2.add(jLabel33);
        jLabel33.setBounds(170, 10, 40, 30);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.setLayout(null);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("CASHIER");
        jPanel3.add(jLabel16);
        jLabel16.setBounds(10, 10, 180, 14);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<html>&#8594;</html>");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(100, 240, 40, 20);

        xButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/deposit.png"))); // NOI18N
        xButton1.setBorderPainted(false);
        xButton1.setCaption("");
        xButton1.setContentAreaFilled(false);
        xButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton1.setName("deposit"); // NOI18N
        xButton1.setText("Deposit");
        xButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(xButton1);
        xButton1.setBounds(120, 220, 90, 70);

        xButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/approve.png"))); // NOI18N
        xButton12.setBorderPainted(false);
        xButton12.setCaption("Issue Online");
        xButton12.setContentAreaFilled(false);
        xButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton12.setName("acceptLiquidation"); // NOI18N
        xButton12.setText("Accept");
        xButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(xButton12);
        xButton12.setBounds(30, 210, 80, 90);

        xButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/deposit.png"))); // NOI18N
        xButton2.setBorderPainted(false);
        xButton2.setCaption("");
        xButton2.setContentAreaFilled(false);
        xButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton2.setName("fundtransfer"); // NOI18N
        xButton2.setText("<html><center>Fund Transfer</center></html>");
        xButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        xButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(xButton2);
        xButton2.setBounds(30, 50, 90, 90);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("<html>&#8594;</html>");
        jPanel3.add(jLabel19);
        jLabel19.setBounds(10, 240, 40, 20);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("<html>&#8594;</html>");
        jPanel3.add(jLabel21);
        jLabel21.setBounds(0, 60, 40, 20);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setLayout(null);

        xButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/cashreceipt.png"))); // NOI18N
        xButton9.setBorderPainted(false);
        xButton9.setCaption("Issue Online");
        xButton9.setContentAreaFilled(false);
        xButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton9.setName("issueReceipt"); // NOI18N
        xButton9.setText("<html><center>Issue Receipt</center></html>");
        xButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(xButton9);
        xButton9.setBounds(90, 210, 70, 80);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("<html>&#8594;</html>");
        jPanel4.add(jLabel18);
        jLabel18.setBounds(180, 240, 40, 20);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("COLLECTOR");
        jPanel4.add(jLabel20);
        jLabel20.setBounds(10, 10, 220, 14);

        xButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/request.png"))); // NOI18N
        xButton11.setBorderPainted(false);
        xButton11.setCaption("");
        xButton11.setContentAreaFilled(false);
        xButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton11.setName("requestAF"); // NOI18N
        xButton11.setText("<html><center>Request AF</center></html>");
        xButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(xButton11);
        xButton11.setBounds(50, 30, 60, 80);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("<html>&#8592;</html>");
        jPanel4.add(jLabel13);
        jLabel13.setBounds(10, 40, 40, 20);

        xButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/remittance.png"))); // NOI18N
        xButton5.setBorderPainted(false);
        xButton5.setCaption("");
        xButton5.setContentAreaFilled(false);
        xButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton5.setName("remit"); // NOI18N
        xButton5.setText("Remit");
        xButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(xButton5);
        xButton5.setBounds(210, 220, 100, 70);

        xButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/batchcapture.png"))); // NOI18N
        xButton8.setBorderPainted(false);
        xButton8.setCaption("<html><center>Issue Online</center></html>");
        xButton8.setContentAreaFilled(false);
        xButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton8.setName("batchCollection"); // NOI18N
        xButton8.setText("<html><center>Batch Capture</center></html>");
        xButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(xButton8);
        xButton8.setBounds(80, 110, 110, 75);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("<html>&#8595;</html>");
        jPanel4.add(jLabel11);
        jLabel11.setBounds(240, 180, 40, 40);

        xButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/verify.png"))); // NOI18N
        xButton14.setBorderPainted(false);
        xButton14.setCaption("Issue Online");
        xButton14.setContentAreaFilled(false);
        xButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton14.setName("issueReceipt"); // NOI18N
        xButton14.setText("Verify");
        xButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(xButton14);
        xButton14.setBounds(210, 110, 110, 75);

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("<html>&#8594;</html>");
        jPanel4.add(jLabel25);
        jLabel25.setBounds(180, 140, 40, 20);

        xButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/liquidation_list.png"))); // NOI18N
        xButton18.setBorderPainted(false);
        xButton18.setCaption("Issue Online");
        xButton18.setContentAreaFilled(false);
        xButton18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton18.setName("creditmemo"); // NOI18N
        xButton18.setText("<html><center>Credit Memo</center></html>");
        xButton18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        xButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xButton18ActionPerformed(evt);
            }
        });
        jPanel4.add(xButton18);
        xButton18.setBounds(220, 10, 90, 90);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel5.setLayout(null);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("ACCOUNTABLE FORM  OFFICER");
        jPanel5.add(jLabel22);
        jLabel22.setBounds(0, 10, 170, 14);

        xButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/issueaf.png"))); // NOI18N
        xButton3.setBorderPainted(false);
        xButton3.setCaption("");
        xButton3.setContentAreaFilled(false);
        xButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton3.setName("issueAF"); // NOI18N
        xButton3.setText("<html>Issue AF</html>");
        xButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel5.add(xButton3);
        xButton3.setBounds(50, 20, 100, 90);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.setLayout(null);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("LIQUIDATING OFFICER");
        jPanel6.add(jLabel23);
        jLabel23.setBounds(10, 10, 160, 14);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("<html>&#8594;</html>");
        jPanel6.add(jLabel15);
        jLabel15.setBounds(110, 240, 40, 20);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("<html>&#8594;</html>");
        jPanel6.add(jLabel17);
        jLabel17.setBounds(10, 240, 40, 20);

        xButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/approve.png"))); // NOI18N
        xButton10.setBorderPainted(false);
        xButton10.setCaption("Issue Online");
        xButton10.setContentAreaFilled(false);
        xButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton10.setName("acceptRemittance"); // NOI18N
        xButton10.setText("Accept");
        xButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(xButton10);
        xButton10.setBounds(40, 210, 80, 90);

        xButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/liquidation_list.png"))); // NOI18N
        xButton13.setBorderPainted(false);
        xButton13.setCaption("Issue Online");
        xButton13.setContentAreaFilled(false);
        xButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton13.setName("remit"); // NOI18N
        xButton13.setText("<html><center>EOR Reconciliation</center></html>");
        xButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        xButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xButton13ActionPerformed(evt);
            }
        });
        jPanel6.add(xButton13);
        xButton13.setBounds(140, 40, 90, 90);

        xButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/liquidation.png"))); // NOI18N
        xButton6.setBorderPainted(false);
        xButton6.setCaption("");
        xButton6.setContentAreaFilled(false);
        xButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton6.setName("liquidate"); // NOI18N
        xButton6.setText("Liquidate");
        xButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(xButton6);
        xButton6.setBounds(140, 220, 100, 70);

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel7.setLayout(null);

        xButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/cashreceipt.png"))); // NOI18N
        xButton15.setBorderPainted(false);
        xButton15.setCaption("Issue Online");
        xButton15.setContentAreaFilled(false);
        xButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton15.setName("issueSubcollectorReceipt"); // NOI18N
        xButton15.setText("<html><center>Issue Receipt (Subcollector)</center><html>");
        xButton15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel7.add(xButton15);
        xButton15.setBounds(70, 30, 120, 100);

        xButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home/icons/remittance.png"))); // NOI18N
        xButton16.setBorderPainted(false);
        xButton16.setCaption("Issue Online");
        xButton16.setContentAreaFilled(false);
        xButton16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        xButton16.setText("<html><center>Subcollector Remittance</center></html>");
        xButton16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel7.add(xButton16);
        xButton16.setBounds(210, 30, 110, 100);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("SUBCOLLECTOR");
        jPanel7.add(jLabel28);
        jLabel28.setBounds(0, 0, 220, 14);

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("<html>&#8593;</html>");
        jPanel7.add(jLabel29);
        jLabel29.setBounds(240, 10, 40, 20);

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("<html>&#8594;</html>");
        jPanel7.add(jLabel30);
        jLabel30.setBounds(170, 60, 40, 20);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void xButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xButton2ActionPerformed

    private void xButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xButton13ActionPerformed

    private void xButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xButton18ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private com.rameses.rcp.control.XButton xButton1;
    private com.rameses.rcp.control.XButton xButton10;
    private com.rameses.rcp.control.XButton xButton11;
    private com.rameses.rcp.control.XButton xButton12;
    private com.rameses.rcp.control.XButton xButton13;
    private com.rameses.rcp.control.XButton xButton14;
    private com.rameses.rcp.control.XButton xButton15;
    private com.rameses.rcp.control.XButton xButton16;
    private com.rameses.rcp.control.XButton xButton17;
    private com.rameses.rcp.control.XButton xButton18;
    private com.rameses.rcp.control.XButton xButton2;
    private com.rameses.rcp.control.XButton xButton3;
    private com.rameses.rcp.control.XButton xButton5;
    private com.rameses.rcp.control.XButton xButton6;
    private com.rameses.rcp.control.XButton xButton7;
    private com.rameses.rcp.control.XButton xButton8;
    private com.rameses.rcp.control.XButton xButton9;
    // End of variables declaration//GEN-END:variables
}
