/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import vista.AgregarUser;
import vista.Login1;

/**
 *
 * @author Usuario
 */
public class AdminPlanta extends javax.swing.JFrame {

    /**
     * Creates new form AdminPlanta
     */
    public AdminPlanta() {
        super("Administrador de Planta");
        initComponents();
        this.setLocationRelativeTo(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        defPlantaFisica = new javax.swing.JButton();
        agregarU = new javax.swing.JButton();
        generarReport = new javax.swing.JButton();
        AtrasAdmin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Administración de la Planta ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 316, 31));

        defPlantaFisica.setText("Definir Planta Fisica");
        defPlantaFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defPlantaFisicaActionPerformed(evt);
            }
        });
        getContentPane().add(defPlantaFisica, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        agregarU.setText("Agregar Usuario");
        agregarU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarUActionPerformed(evt);
            }
        });
        getContentPane().add(agregarU, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, -1));

        generarReport.setText("Generar Reporte");
        getContentPane().add(generarReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, -1, -1));

        AtrasAdmin.setText("Atras");
        AtrasAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtrasAdminActionPerformed(evt);
            }
        });
        getContentPane().add(AtrasAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/fondo.jpeg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, 0, 410, 260));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void defPlantaFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defPlantaFisicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_defPlantaFisicaActionPerformed

    private void agregarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarUActionPerformed
        AgregarUser newU  = new AgregarUser();
        newU.setVisible(true);
        dispose();
    }//GEN-LAST:event_agregarUActionPerformed

    private void AtrasAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtrasAdminActionPerformed
        Login1 log  = new Login1();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_AtrasAdminActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminPlanta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPlanta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPlanta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPlanta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPlanta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AtrasAdmin;
    private javax.swing.JButton agregarU;
    private javax.swing.JButton defPlantaFisica;
    private javax.swing.JButton generarReport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
