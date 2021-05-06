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
import modelo.Habitacion;
import modelo.Reserva;
import modelo.Reservahabitacion;
import modelo.ReservahabitacionPK;
import persistencia.ClienteJpaController;
import persistencia.HabitacionJpaController;
import persistencia.ReservahabitacionJpaController;


/**
 *
 * @author Usuario
 */
public class RegReserva extends javax.swing.JFrame {
    
    private usuarioLogica usuario = new usuarioLogica();
    private ClienteJpaController cliente = new ClienteJpaController();
    private HabitacionJpaController habitacion = new HabitacionJpaController();
    private ReservahabitacionJpaController reservaH = new ReservahabitacionJpaController();
    List<Habitacion> habitacionesD;
    String tipo="";
   
    public RegReserva() {
        initComponents();
        this.setLocationRelativeTo(null);

    }

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
        crearR = new javax.swing.JButton();
        newR = new javax.swing.JButton();
        Name = new javax.swing.JTextField();
        Apellido = new javax.swing.JTextField();
        idR = new javax.swing.JTextField();
        Telefono = new javax.swing.JTextField();
        FechaInicA = new javax.swing.JTextField();
        numeroP = new javax.swing.JTextField();
        AtrasR = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        FechaInicM = new javax.swing.JTextField();
        FechaInicD = new javax.swing.JTextField();
        FechaFinM = new javax.swing.JTextField();
        FechaFinD = new javax.swing.JTextField();
        FechaRealizA = new javax.swing.JTextField();
        FechaRealizM = new javax.swing.JTextField();
        FechaRealizD = new javax.swing.JTextField();
        FechaFinA = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        disponibles = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tipoHabitacion = new javax.swing.JComboBox<>();
        buscarHab = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reserva Habitación");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 11, 212, 32));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nombre :");
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 134, 129, 25));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Apellidos:");
        jLabel3.setOpaque(true);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 97, 129, 25));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("ID:");
        jLabel4.setOpaque(true);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 61, 129, 25));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Telefono:");
        jLabel5.setOpaque(true);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 170, 129, 25));

        jLabel6.setText("Fecha Inicio(aaaa/mm/dd):");
        jLabel6.setOpaque(true);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, 26));

        jLabel7.setText("Fecha Fin(aaaa/mm/dd):");
        jLabel7.setOpaque(true);
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 129, 25));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("No. Personas:");
        jLabel8.setOpaque(true);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 129, 25));

        crearR.setText("Crear Reserva ");
        crearR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearRActionPerformed(evt);
            }
        });
        getContentPane().add(crearR, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, -1, -1));

        newR.setText("Nueva Reserva");
        newR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRActionPerformed(evt);
            }
        });
        getContentPane().add(newR, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, -1, -1));

        Name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameActionPerformed(evt);
            }
        });
        getContentPane().add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 133, 205, 25));

        Apellido.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 98, 205, 24));

        idR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idRActionPerformed(evt);
            }
        });
        getContentPane().add(idR, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 61, 205, 25));

        Telefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(Telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 170, 205, 22));

        FechaInicA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(FechaInicA, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 48, 26));

        numeroP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(numeroP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 205, 25));

        AtrasR.setText("Atras");
        AtrasR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtrasRActionPerformed(evt);
            }
        });
        getContentPane().add(AtrasR, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel9.setText("Fecha de realización(aaaa/mm/dd):");
        jLabel9.setOpaque(true);
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 170, 32));

        FechaInicM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        FechaInicM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechaInicMActionPerformed(evt);
            }
        });
        getContentPane().add(FechaInicM, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 48, 26));

        FechaInicD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(FechaInicD, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 49, 26));

        FechaFinM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        FechaFinM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechaFinMActionPerformed(evt);
            }
        });
        getContentPane().add(FechaFinM, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 48, 26));

        FechaFinD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(FechaFinD, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 48, 26));

        FechaRealizA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(FechaRealizA, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 48, 26));

        FechaRealizM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        FechaRealizM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechaRealizMActionPerformed(evt);
            }
        });
        getContentPane().add(FechaRealizM, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 48, 26));

        FechaRealizD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(FechaRealizD, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 48, 26));

        FechaFinA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        FechaFinA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechaFinAActionPerformed(evt);
            }
        });
        getContentPane().add(FechaFinA, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 48, 26));

        consultar.setText("Consultar cliente");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        getContentPane().add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 61, 117, -1));

        disponibles.setToolTipText("");
        disponibles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                disponiblesItemStateChanged(evt);
            }
        });
        disponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disponiblesActionPerformed(evt);
            }
        });
        getContentPane().add(disponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 200, 30));

        jLabel10.setText("Habitaciones disponibles:");
        jLabel10.setOpaque(true);
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 130, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Tipo habitación:");
        jLabel12.setOpaque(true);
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 140, 30));

        tipoHabitacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "sencilla", "doble", "familiar" }));
        tipoHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoHabitacionActionPerformed(evt);
            }
        });
        getContentPane().add(tipoHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 200, 30));

        buscarHab.setText("Buscar Habitación");
        buscarHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarHabActionPerformed(evt);
            }
        });
        getContentPane().add(buscarHab, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 120, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/fondo.jpeg"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            
            Reservahabitacion reservaN = new Reservahabitacion();
            ReservahabitacionPK reser = new ReservahabitacionPK();
            
            reservaN.setReserva(nuevaReserva);
            reservaN.setFecharInicio(fechaInicio);
            reservaN.setFechaFin(fechaFin);
            reservaN.setNumeroPersonas(personas);
            reservaN.setHabitacion(habitacion.findHabitacion(disponibles.getSelectedItem().toString()));
            reser.setCodReserva(nuevaReserva.getCodigoR());
            reser.setNumHabitacion(disponibles.getSelectedItem().toString());
            reservaN.setReservahabitacionPK(reser);
            reservaN.setEstadoHabitacion("Reservada");
            
            usuario.crearReservaHabitacion(reservaN, reser);

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

    private void NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameActionPerformed

    private void AtrasRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtrasRActionPerformed
        Recepcionist recep  = new Recepcionist();
        recep.setVisible(true);
        dispose();
    }//GEN-LAST:event_AtrasRActionPerformed

    private void idRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idRActionPerformed

    }//GEN-LAST:event_idRActionPerformed

    private void FechaInicMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaInicMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaInicMActionPerformed

    private void FechaFinMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaFinMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaFinMActionPerformed

    private void FechaRealizMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaRealizMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaRealizMActionPerformed

    private void FechaFinAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaFinAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaFinAActionPerformed

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

    private void newRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newRActionPerformed
        RegReserva reserv  = new RegReserva();
        reserv.setVisible(true);
        dispose();
    }//GEN-LAST:event_newRActionPerformed

    private void disponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disponiblesActionPerformed

    }//GEN-LAST:event_disponiblesActionPerformed

    private void disponiblesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_disponiblesItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_disponiblesItemStateChanged

    private void buscarHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarHabActionPerformed
        habitacionesD = usuario.habitaciones();
        tipo = tipoHabitacion.getSelectedItem().toString();
        disponibles.setModel(new javax.swing.DefaultComboBoxModel<>(usuario.habitacionesDisponibles(habitacionesD, tipo)));
    }//GEN-LAST:event_buscarHabActionPerformed

    private void tipoHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoHabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoHabitacionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        /*
        HabitacionJpaController h = new HabitacionJpaController();
        ReservahabitacionJpaController e = new ReservahabitacionJpaController();*/
        
        
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
            java.util.logging.Logger.getLogger(RegReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegReserva().setVisible(true);
            }
        });
/*
            int anioR = Integer.parseInt("2018");
            int mesR = Integer.parseInt("12");
            int diaR = Integer.parseInt("30");
            
            int FechaIA = Integer.parseInt("2018");
            int FechaIM = Integer.parseInt("12");
            int FechaID = Integer.parseInt("31");
            
            int FechaFA = Integer.parseInt("2018");
            int FechaFM = Integer.parseInt("12");
            int FechaFD = Integer.parseInt("20");
            
            int personas = 4;
            
            Date fechaRealizacion = new Date(anioR-1900,mesR-1,diaR);
            Date fechaInicio = new Date(FechaIA-1900,FechaIM-1,FechaID);
            Date fechaFin = new Date(FechaFA-1900,FechaFM-1,FechaFD);
            
            Cliente cliente1 = new Cliente();
            cliente1.setIdentificacion("123");
            cliente1.setNombres("Cristian");
            cliente1.setApellidos("Ospina");
            cliente1.setTelefono("2300582");
            
        
            Reserva nuevaReserva = new Reserva();
            nuevaReserva.setFechaDeRealizacion(fechaRealizacion);
            nuevaReserva.setEstado("Inactiva");
            nuevaReserva.setCodigoCliente(cliente1);
            nuevaReserva.setCodigoR(6);
            System.out.println(nuevaReserva.getCodigoR());
            //usuario.registrarReserva(nuevaReserva);
            
            Reservahabitacion reservaN = new Reservahabitacion();
            ReservahabitacionPK reser = new ReservahabitacionPK();
            reservaN.setReserva(nuevaReserva);
            reservaN.setFecharInicio(fechaInicio);
            reservaN.setFechaFin(fechaFin);
            reservaN.setNumeroPersonas(personas);
            reservaN.setHabitacion(h.findHabitacion("1"));
            reser.setCodReserva(nuevaReserva.getCodigoR());
            reser.setNumHabitacion("1");
            reservaN.setReservahabitacionPK(reser);
            
            e.create(reservaN);*/
           
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apellido;
    private javax.swing.JButton AtrasR;
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
    private javax.swing.JButton buscarHab;
    private javax.swing.JButton consultar;
    private javax.swing.JButton crearR;
    private javax.swing.JComboBox<String> disponibles;
    private javax.swing.JTextField idR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JComboBox<String> tipoHabitacion;
    // End of variables declaration//GEN-END:variables
}
