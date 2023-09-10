package dominio.notificaciones;

import dominio.comunidad.MedioDeNotificaion;
import dominio.notificaciones.adapter.AdapterJavaxMail;
import dominio.notificaciones.strategys.EstrategiaDeNotificacion;
import dominio.comunidad.CuandoNotificar;
import dominio.notificaciones.adapter.AdapterTwilio;
import dominio.notificaciones.strategys.Mail;
import dominio.notificaciones.strategys.Whatsapp;
import dominio.servicios.Incidente;
import dominio.actores.Ciudadano;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Notificador {

    private List<Notificacion> notificacionesPorEnviar;
    private EstrategiaDeNotificacion strategy;
    private ScheduledExecutorService servicioPlanificador;

    // contructor
    public Notificador(){
        this.notificacionesPorEnviar = new ArrayList<>();
        this.servicioPlanificador = Executors.newScheduledThreadPool(1);
    }

    public void notificar(Notificacion notificacion) {
        System.out.println(notificacion.crearMensaje());
    //    this.cambiarEstrategia( medioDeNotificaion(notificacion) );
    //    strategy.enviar(notificacion.getDestinatario(), notificacion.crearMensaje());
    }

    public void notificarAlInstante(Ciudadano persona, Incidente incidente){
        Notificacion nuevaNotificacion = new Notificacion(persona, incidente); // se agregara mas cosas a la notificacion
        this.notificar(nuevaNotificacion);
    }

    public void notificarMiembroSegunSuForma(Ciudadano persona, Incidente incidente){
        if (persona.getFormadenotificacion() == CuandoNotificar.CUANDOSUCEDEN){
            this.notificarAlInstante(persona,incidente);
        }
        if(persona.getFormadenotificacion() == CuandoNotificar.SINAPUROS){
            miembroDisponibleParaNotificar(persona, incidente);
        }
    }

    public void miembroDisponibleParaNotificar(Ciudadano miembro, Incidente incidente){
        if( this.horarioPasado(miembro) ){
            notificarAlInstante(miembro, incidente);
        }
        else{
            programarNotificacion(miembro, incidente);
        }
    }

    public void programarNotificacion(Ciudadano persona, Incidente incidente){
        if( notificacionesPorEnviar.stream().anyMatch( m -> m.getDestinatario().equals(persona)) ){
            notificacionesPorEnviar.get( posicionDePersona(persona) ).evaluarIncidenteParaNotificar(incidente);
        }
        else{
            Notificacion nuevaNotificacion = new Notificacion(persona, incidente);
            this.notificacionesPorEnviar.add(nuevaNotificacion);
        }
    }

    public EstrategiaDeNotificacion medioDeNotificaion(Notificacion notificacion){
        if (notificacion.getDestinatario().getMedioDeNotificaion() == MedioDeNotificaion.MAIL){
            return new Mail("","");
        }
        if (notificacion.getDestinatario().getMedioDeNotificaion() == MedioDeNotificaion.WHATSAPP){
            return new Whatsapp("","","");
        }
        else {
            return null;
        }
    }

    public void cambiarEstrategia(EstrategiaDeNotificacion strategy){
        this.strategy = strategy;
    }

    public void iniciarEnvioAsincronico(){
        servicioPlanificador.scheduleAtFixedRate(() -> envioAsincronico(), 0, 10,TimeUnit.SECONDS);
    }

    public void envioAsincronico(){
        for ( Notificacion notificacion : this.notificacionesPorEnviar){
            if (this.horarioPasado(notificacion.getDestinatario())){
                notificar(notificacion);
                this.notificacionesPorEnviar.remove(notificacion);
            }
        }
    }

    public boolean horarioPasado(Ciudadano miembro){
        return miembro.getHorarioDeNotificaion().compareTo(LocalTime.now()) < 0;
    }

    public int posicionDePersona( Ciudadano miembroBuscado ){
        int posicion = 0;
        for (Notificacion notificacion : this.notificacionesPorEnviar ){
            if(notificacion.getDestinatario().equals(miembroBuscado)){
                return posicion;
            }
            posicion ++;
        }
        return -1;
    }

    public ScheduledExecutorService getServicioPlanificador() {
        return servicioPlanificador;
    }

    public void cerrarServicio(){
        this.servicioPlanificador.shutdown();
    }

}





