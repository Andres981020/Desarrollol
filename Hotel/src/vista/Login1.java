package vista;


import java.util.List;
import javax.swing.JOptionPane;
import logica.usuarioLogica;
import modelo.Usuario;
import vista.Recepcionist;



public class Login1 extends javax.swing.JFrame {
    
    private usuarioLogica usu = new usuarioLogica();
    private List<Usuario> usuarios;
     
    public Login1() {
        super("Gestion Hospedaje Hotel Vacaciones Vallunas");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LabelPassword = new javax.swing.JLabel();
        PasswordUser = new javax.swing.JPasswordField();
        Ingresar = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        LabelName = new javax.swing.JLabel();
        NameUser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabelPassword.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LabelPassword.setText("Contraseña:");
        LabelPassword.setOpaque(true);
        getContentPane().add(LabelPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 162, 25));

        PasswordUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordUserActionPerformed(evt);
            }
        });
        getContentPane().add(PasswordUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 154, 25));

        Ingresar.setText("Ingresar");
        Ingresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IngresarMouseClicked(evt);
            }
        });
        Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IngresarActionPerformed(evt);
            }
        });
        getContentPane().add(Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, -1, -1));

        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        getContentPane().add(Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, -1, -1));

        LabelName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LabelName.setText("Nombre Usuario:");
        LabelName.setOpaque(true);
        getContentPane().add(LabelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 162, 25));
        LabelName.getAccessibleContext().setAccessibleName("Nombre Usuario:");

        getContentPane().add(NameUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 154, 25));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/hoteles.jpeg"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private void IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IngresarActionPerformed
        usuarios = usu.consultar();
        boolean bool = true;
 
        for(Usuario u: usuarios){
            
            if(NameUser.getText().equals(u.getUser()) && PasswordUser.getText().equals(u.getPassword()) && u.getTipoUsuario().equals("Recepcionista")){
                Recepcionist recep  = new Recepcionist();
                recep.setVisible(true);
                dispose();
                bool = false;
                break;
            }
            else if(NameUser.getText().equals(u.getUser()) && PasswordUser.getText().equals(u.getPassword()) && u.getTipoUsuario().equals("Administrador de planta")){
                AdminPlanta adminP = new AdminPlanta();
                adminP.setVisible(true);
                dispose();
                bool = false;
                break;
            }
            else if(NameUser.getText().equals(u.getUser()) && PasswordUser.getText().equals(u.getPassword()) && u.getTipoUsuario().equals("Encargado de restaurante")){
                EncargadoRest encarg  = new EncargadoRest();
                encarg.setVisible(true);
                dispose();
                bool = false;
                break;
            }
        }   
        
        if(bool){
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecto");
            NameUser.setText("");
            PasswordUser.setText("");
        }
    }//GEN-LAST:event_IngresarActionPerformed

    private void PasswordUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordUserActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SalirActionPerformed

    private void IngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IngresarMouseClicked
        
    }//GEN-LAST:event_IngresarMouseClicked

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
            java.util.logging.Logger.getLogger(Login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ingresar;
    private javax.swing.JLabel LabelName;
    private javax.swing.JLabel LabelPassword;
    private javax.swing.JTextField NameUser;
    private javax.swing.JPasswordField PasswordUser;
    private javax.swing.JButton Salir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}