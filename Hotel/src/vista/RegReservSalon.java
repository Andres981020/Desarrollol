/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import logica.usuarioLogica;
import modelo.Cliente;
import modelo.Reserva;
import modelo.Reservahabitacion;
import modelo.ReservahabitacionPK;
import modelo.Reservasalon;
import modelo.ReservasalonPK;
import modelo.Salon;
import persistencia.ClienteJpaController;
import persistencia.HabitacionJpaController;
import persistencia.ReservahabitacionJpaController;
import persistencia.SalonJpaController;

/**
 *
 * @author Lenovo
 */
public class RegReservSalon extends javax.swing.JFrame {

    private usuarioLogica usuario = new usuarioLogica();
    private ClienteJpaController cliente = new ClienteJpaController();
    private HabitacionJpaController habitacion = new HabitacionJpaController();
    private ReservahabitacionJpaController reservaH = new ReservahabitacionJpaController();
    private SalonJpaController salon = new SalonJpaController();
    List<Salon> salonD;
    String tipo="";
    /**
     * Creates new form RegReservSalon
     */
    public RegReservSalon() {
        initComponents();
        salonD = usuario.salones();
        salonDisponibles.setModel(new javax.swing.DefaultComboBoxModel<>(usuario.salonesDisponibles(salonD)));
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tipoSalon = new javax.swing.JLabel();
        salonDisponibles = new javax.swing.JComboBox<>();
        FechaRealizA = new javax.swing.JTextField();
        idR = new javax.swing.JTextField();
        Name = new javax.swing.JTextField();
        Apellido = new javax.swing.JTextField();
        Telefono = new javax.swing.JTextField();
        FechaInicA = new javax.swing.JTextField();
        FechaFinA = new javax.swing.JTextField();
        FechaInicM = new javax.swing.JTextField();
        FechaInicD = new javax.swing.JTextField();
        FechaFinM = new javax.swing.JTextField();
        FechaFinD = new javax.swing.JTextField();
        numeroP = new javax.swing.JTextField();
        FechaRealizM = new javax.swing.JTextField();
        FechaRealizD = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        consultar = new javax.swing.JButton();
        crearR = new javax.swing.JButton();
        newR = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("ID:");
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 83, 129, 25));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nombre:");
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 119, 129, 25));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Apellido:");
        jLabel3.setOpaque(true);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 155, 129, 25));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Telefono:");
        jLabel4.setOpaque(true);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 191, 129, 25));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Inicio(yyyy-mm-dd):");
        jLabel5.setOpaque(true);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 227, -1, 25));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Fecha Fin(yyyy-mm-dd):");
        jLabel6.setOpaque(true);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 263, 150, 25));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("No. Personas:");
        jLabel7.setOpaque(true);
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 299, 129, 25));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Fecha Realizado(yyyy-mm-dd):");
        jLabel8.setOpaque(true);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 335, 190, 25));

        tipoSalon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tipoSalon.setText("Salones Disponibles:");
        tipoSalon.setOpaque(true);
        getContentPane().add(tipoSalon, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 371, 159, 25));

        getContentPane().add(salonDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 371, 193, 25));
        getContentPane().add(FechaRealizA, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 335, 40, 25));
        getContentPane().add(idR, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 83, 193, 30));
        getContentPane().add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 119, 193, 25));
        getContentPane().add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 155, 193, 25));

        Telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelefonoActionPerformed(evt);
            }
        });
        getContentPane().add(Telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 191, 193, 25));
        getContentPane().add(FechaInicA, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 227, 38, 25));
        getContentPane().add(FechaFinA, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 263, 40, 25));
        getContentPane().add(FechaInicM, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 227, 37, 25));
        getContentPane().add(FechaInicD, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 227, 38, 25));
        getContentPane().add(FechaFinM, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 263, 37, 25));
        getContentPane().add(FechaFinD, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 263, 38, 25));
        getContentPane().add(numeroP, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 299, 193, 25));
        getContentPane().add(FechaRealizM, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 335, 38, 25));
        getContentPane().add(FechaRealizD, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 335, 35, 25));

        jButton1.setText("Atras");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Reserva Salon");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 246, 38));

        consultar.setText("Consultar Cliente");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        getContentPane().add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 83, -1, 30));

        crearR.setText("Crear Reserva");
        crearR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearRActionPerformed(evt);
            }
        });
        getContentPane().add(crearR, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 414, -1, 31));

        newR.setText("Nueva Reserva");
        newR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRActionPerformed(evt);
            }
        });
        getContentPane().add(newR, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 414, -1, 31));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/fondo.jpeg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, 0, 560, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TelefonoActionPerformed

    private void newRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newRActionPerformed
        RegReservSalon r = new RegReservSalon();
        r.setVisible(true);
        dispose();
    }//GEN-LAST:event_newRActionPerformed

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        Cliente cli = cliente.findCliente(idR.getText());
        if(cli != null){
            Apellido.setText(cli.getApellidos());
            Name.setText(cli.getNombres());
            Telefono.setText(cli.getTelefono());
        } 
        else{
            RegistroCliente reg = new RegistroCliente();
            JOptionPane.showMessageDialog(this, "El cliente no existe, debe ser registrado");
            reg.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_consultarActionPerformed

    private void crearRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearRActionPerformed
        try{
            int anioR = Integer.parseInt(FechaRealizA.getText().toString());
            int mesR = Integer.parseInt(FechaRealizM.getText().toString());
            int diaR = Integer.parseInt(FechaRealizD.getText().toString());
            
            int FechaIA = Integer.parseInt(FechaInicA.getText().toString());
            int FechaIM = Integer.parseInt(FechaInicM.getText().toString());
            int FechaID = Integer.parseInt(FechaInicD.getText().toString());
            
            int FechaFA = Integer.parseInt(FechaFinA.getText().toString());
            int FechaFM = Integer.parseInt(FechaFinM.getText().toString());
            int FechaFD = Integer.parseInt(FechaFinD.getText().toString());
            
            int personas = Integer.valueOf(numeroP.getText());
            
            Date fechaRealizacion = new Date(anioR-1900,mesR-1,diaR);
            Date fechaInicio = new Date(FechaIA-1900,FechaIM-1,FechaID);
            Date fechaFin = new Date(FechaFA-1900,FechaFM-1,FechaFD);
            

            Reserva nuevaReserva = new Reserva();
            nuevaReserva.setFechaDeRealizacion(fechaRealizacion);
            nuevaReserva.setEstado("Inactiva");
            nuevaReserva.setCodigoCliente(cliente.findCliente(idR.getText()));
            nuevaReserva.setCodigoR(usuario.numeroUltimaReserva());
            System.out.println(nuevaReserva.getCodigoR());
            usuario.registrarReserva(nuevaReserva);
            
            Reservasalon reservaN = new Reservasalon();
            ReservasalonPK reser = new ReservasalonPK();
            
            reservaN.setReserva(nuevaReserva);
            reservaN.setFecharInicio(fechaInicio);
            reservaN.setFechaFin(fechaFin);
            reservaN.setNumeroPersonas(personas);
            reservaN.setSalon(salon.findSalon(salonDisponibles.getSelectedItem().toString()));
            reser.setCodReserva(nuevaReserva.getCodigoR());
            reser.setNumSalon(salonDisponibles.getSelectedItem().toString());
            reservaN.setReservasalonPK(reser);
            
            usuario.crearReservaSalon(reservaN, reser);

            /*
            Habitacion hab = habitacion.findHabitacion(disponibles.getSelectedItem().toString());
            hab.setEstado("reservada");
            habitacion.edit(hab);
            */
   
            JOptionPane.showMessageDialog(this, "La reserva se ha registrado satisfactoriamente");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_crearRActionPerformed

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
            java.util.logging.Logger.getLogger(RegReservSalon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegReservSalon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegReservSalon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegReservSalon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegReservSalon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apellido;
    private javax.swing.JTextField FechaFinA;
    private javax.swing.JTextField FechaFinD;
    private javax.swing.JTextField FechaFinM;
    private javax.swing.JTextField FechaInicA;
    private javax.swing.JTextField FechaInicD;
    private javax.swing.JTextField FechaInicM;
    private javax.swing.JTextField FechaRealizA;
    private javax.swing.JTextField FechaRealizD;
    private javax.swing.JTextField FechaRealizM;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField Telefono;
    private javax.swing.JButton consultar;
    private javax.swing.JButton crearR;
    private javax.swing.JTextField idR;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton newR;
    private javax.swing.JTextField numeroP;
    private javax.swing.JComboBox<String> salonDisponibles;
    private javax.swing.JLabel tipoSalon;
    // End of variables declaration//GEN-END:variables
}
