/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rameses.gov.treasury.tool.views;

/**
 *
 * @author ramesesinc
 */
public class RebuildProcPage extends javax.swing.JPanel {

    /**
     * Creates new form RebuildProcPage
     */
    public RebuildProcPage() {
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

        xLabel3 = new com.rameses.rcp.control.XLabel();
        xLabel2 = new com.rameses.rcp.control.XLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 20, 20));
        setPreferredSize(new java.awt.Dimension(503, 162));
        setLayout(new com.rameses.rcp.control.layout.XLayout());

        xLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        xLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 0, 5));
        xLabel3.setIconResource("com/rameses/rcp/icons/loading16.gif");
        add(xLabel3);

        xLabel2.setExpression("#{message}");
        xLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        xLabel2.setFontStyle("font-size:20;");
        add(xLabel2);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XLabel xLabel2;
    private com.rameses.rcp.control.XLabel xLabel3;
    // End of variables declaration//GEN-END:variables
}
