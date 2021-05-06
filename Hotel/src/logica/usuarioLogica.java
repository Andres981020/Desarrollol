/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Habitacion;
import modelo.Reserva;
import modelo.Reservahabitacion;
import modelo.ReservahabitacionPK;
import modelo.Reservasalon;
import modelo.ReservasalonPK;
import modelo.Salon;
import modelo.Usuario;
import persistencia.ClienteJpaController;
import persistencia.HabitacionJpaController;
import persistencia.ReservaJpaController;
import persistencia.ReservahabitacionJpaController;
import persistencia.ReservasalonJpaController;
import persistencia.SalonJpaController;
import persistencia.UsuarioJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class usuarioLogica {

    private UsuarioJpaController usuario = new UsuarioJpaController();
    private ClienteJpaController cliente = new ClienteJpaController();
    private ReservaJpaController reserva = new ReservaJpaController();
    private HabitacionJpaController habitacion = new HabitacionJpaController();
    private ReservahabitacionJpaController reservaH = new ReservahabitacionJpaController();
    private ReservasalonJpaController reservaS = new ReservasalonJpaController();
    private SalonJpaController salon = new SalonJpaController();
    private List<Reserva> reservas;
    
    public usuarioLogica(){
    } 
    
    public void registrarCliente(Cliente c) throws Exception{
        if(c == null){
            throw new Exception("El cliente no tiene información");
        }
        Cliente cli = cliente.findCliente(c.getIdentificacion());
        if(cli == null){
            cliente.create(c);
        }else{
            throw new Exception("Cliente ya registrado");
        } 
    }
    
    public void registrarReserva(Reserva r) throws Exception{
        if(r == null){
            throw new Exception("La reserva no tiene información");
        }
        Reserva reser = reserva.findReserva(r.getCodigoR());
        if(reser == null){
            reserva.create(r);
        }else{
            throw new Exception("Reserva ya registrada");
        } 
    }

    public List<Usuario> consultar(){
        return usuario.findUsuarioEntities();
    }
    
    public List<Habitacion> habitaciones(){
        return habitacion.findHabitacionEntities();
    }
    public List<Reservahabitacion> reservasH(){
        return reservaH.findReservahabitacionEntities();
    }
    
    public void modificarReserva(Reserva r){
        try {
            reserva.edit(r);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(usuarioLogica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(usuarioLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Reserva> reservas(){
        return reserva.findReservaEntities();
    }
    
    public String[] habitacionesDisponibles(List<Habitacion> hab, String tipo){
        usuarioLogica usuarioL = new usuarioLogica();
        hab = usuarioL.habitaciones();
        int tam = hab.size();
        String resul[] = new String[tam];
        int i=0;
        for(Habitacion h: hab){
            if(h.getTipo().equals(tipo)){
                resul[i] = h.getNumeroHabitacion(); 
                i++;
            }
        }
        return resul;
    }
    
    public List<Reservahabitacion> habitacionesReservadas(){
        return reservaH.findReservahabitacionEntities();
    }
    
    public String[] reservasHechas(List<Reserva> res, Cliente c){
        usuarioLogica usuarioL = new usuarioLogica();
        res = usuarioL.reservas();
        int tam = res.size();
        String resul[] = new String[tam];
        int i=0;
        for(Reserva r: res){
            if(r.getCodigoCliente().getIdentificacion().equals(c.getIdentificacion()) && r.getEstado().equals("Inactiva")){
                String codCli = Integer.toString(r.getCodigoR());
                resul[i] = codCli;
                i++;
            }
        }
        return resul;
    }
    
    public int numeroUltimaReserva(){
        int codultReserva = 0;
        reservas = reserva.findReservaEntities();
        
        for(Reserva r: reservas){
            codultReserva = r.getCodigoR();
        }
        return codultReserva+1;
    }
    
    public void crearReservaHabitacion(Reservahabitacion rh, ReservahabitacionPK pk) throws Exception{
        if(rh == null){
            throw new Exception("La reserva no tiene información");
        }
        Reservahabitacion reser = reservaH.findReservahabitacion(pk);
        if(reser == null){
            reservaH.create(rh);
        }else{
            throw new Exception("Reserva ya registrada");
        } 
    }
    
    public void crearReservaSalon(Reservasalon rh, ReservasalonPK pk) throws Exception{
        if(rh == null){
            throw new Exception("La reserva no tiene información");
        }
        Reservasalon reser = reservaS.findReservasalon(pk);
        if(reser == null){
            reservaS.create(rh);
        }else{
            throw new Exception("Reserva ya registrada");
        } 
    }
    
    public int buscarReserva(String c){
        String codigo = "";
        reservas = reserva.findReservaEntities();
        
        for(Reserva r: reservas){
            if(r.getCodigoCliente().getIdentificacion().equals(c)){
                return r.getCodigoR();
            }
        }
        return 0;
    }
    
    public String [] buscarReservaInactiva(String c){
        usuarioLogica usuarioL = new usuarioLogica();
        List<Reserva> res = usuarioL.reservas();
        int tam = res.size();
        String resul[] = new String[tam];
        int i=0;
        for(Reserva r: res){
            if(r.getCodigoCliente().getIdentificacion().equals(c) && r.getEstado().equals("Inactiva")){
                String codCli = Integer.toString(r.getCodigoR());
                resul[i] = codCli;
                i++;
            }
        }
        
        return resul;
    }
    
    public Reserva consultarReserva(int codR){
        return reserva.findReserva(codR);
    }
    
    public Cliente retornarCliente(String id){
        return cliente.findCliente(id);
    }
    
    public void cambiarEstadoHabitacion(){
        Habitacion h = new Habitacion();
        
    }
    
    public List<Salon> salones(){
        return salon.findSalonEntities();
    }
    
    
    public String[] salonesDisponibles(List<Salon> salones){
        usuarioLogica usuarioL = new usuarioLogica();
        salones = usuarioL.salones();
        int tam = salones.size();
        String resul[] = new String[tam];
        int i=0;
        for(Salon s: salones){
            if(s.getEstado().equals("disponible")){
                resul[i] = s.getNumeroSalon();
                i++;
            }
        }
        return resul;
    }

    public static void main(String args[]) throws Exception {
        
        usuarioLogica us = new usuarioLogica();
        ReservaJpaController reserva = new ReservaJpaController();
        
        /*
        List<Habitacion> h = us.habitaciones();
        String resul[] = new String[h.size()];
        resul = us.habitacionesDisponibles(h);
        for(int i=0; i<resul.length; i++){
            System.out.println(resul[i]);
        }
        
        Cliente cliente1 = new Cliente();
        cliente1.setIdentificacion("123");
        cliente1.setNombres("Cristian");
        cliente1.setApellidos("Ospina");
        cliente1.setTelefono("2300582");
        
        usuarioLogica u = new usuarioLogica();
        u.registrarCliente(cliente1);
        //Cliente c = cliente.findCliente("123");
        //us.buscarReserva(c);
       //System.out.println(us.buscarReserva(cliente1.getIdentificacion()));
       Reserva r = reserva.findReserva(1);
       System.out.println(r.getEstado());
       System.out.println(r.getCodigoCliente());
       System.out.println(us.buscarReserva(cliente1.getIdentificacion()));
       */
       
       
       
       
        
    }
    
}


