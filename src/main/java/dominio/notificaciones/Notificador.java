package dominio.notificaciones;

import dominio.comunidad.MedioDeNotificaion;
import dominio.notificaciones.adapter.MailAdapter;
import dominio.notificaciones.adapter.NotificadorAdapter;
import dominio.comunidad.FormaDeNotificacion;
import dominio.notificaciones.adapter.WhatsappAdapter;
import dominio.servicios.Incidente;
import dominio.comunidad.Miembro;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Notificador {

    HashMap<Miembro,Tarea> notificacionesPorEnviar;
    NotificadorAdapter adapter;
    ScheduledExecutorService servicioPlanificador;

    // contructor
    public Notificador(){
        this.notificacionesPorEnviar = new HashMap<>();
        this.servicioPlanificador = Executors.newScheduledThreadPool(0);
    }

    public void notificar(Notificacion notificacion) {
        System.out.println(notificacion.crearMensaje());
        //this.adapter = this.medioDeNotificaion(notificacion);
        //adapter.enviar(notificacion.getDestinatario(), notificacion.crearMensaje());
    }

    public void notificarAlInstante(Miembro persona, Incidente incidente){
        Notificacion nuevaNotificacion = new Notificacion(persona, incidente); // se agregara mas cosas a la notificacion
        this.notificar(nuevaNotificacion);
    }

    public void notificarMiembroSegunSuForma(Miembro persona, Incidente incidente){
        if (persona.getFormadenotificacion() == FormaDeNotificacion.CUANDOSUCEDEN){
            this.notificarAlInstante(persona,incidente);
        }
        if(persona.getFormadenotificacion() == FormaDeNotificacion.SINAPUROS){
            miembroDisponibleParaNotificar(persona, incidente);
            //this.cerrarServicio(); // fijarse lu ego

        }
    }

    public void miembroDisponibleParaNotificar(Miembro miembro, Incidente incidente){
        if( miembro.getHorarioDeNotificaion().compareTo(LocalTime.now()) < 0 ){
            notificarAlInstante(miembro, incidente);
        }
        else{
            programarNotificacion(miembro, incidente);
        }
    }

    public void programarNotificacion(Miembro persona, Incidente incidente){
        if( notificacionesPorEnviar.containsKey(persona) ){
            notificacionesPorEnviar.get(persona).evaluarIncidente(incidente);
        }
        else{
            Notificacion nuevaNotificacion = new Notificacion(persona, incidente);
            Tarea tarea = new Tarea(nuevaNotificacion, medioDeNotificaion(nuevaNotificacion));
            servicioPlanificador.schedule(tarea, tarea.calcularTiempoRestante(persona.getHorarioDeNotificaion()), TimeUnit.SECONDS);
            notificacionesPorEnviar.put(persona,tarea);
        }
    }

    public NotificadorAdapter medioDeNotificaion(Notificacion notificacion){
        if (notificacion.getDestinatario().getMedioDeNotificaion() == MedioDeNotificaion.MAIL){
            return new MailAdapter("","");
        }
        else{
            return new WhatsappAdapter("","","");
        }
    }

    public ScheduledExecutorService getServicioPlanificador() {
        return servicioPlanificador;
    }

    public void cerrarServicio(){
        this.servicioPlanificador.shutdown();
    }

    // clase que nada mas la usaremos aca
    static class Tarea implements Runnable {
        private Notificacion notificacion;
        private NotificadorAdapter adapter;

        public Tarea(Notificacion notificacion, NotificadorAdapter adapter) {
            this.notificacion = notificacion;
            this.adapter = adapter;
        }

        public void evaluarIncidente(Incidente incidente) {
            notificacion.evaluarIncidenteParaNotificar(incidente);
        }

        public long calcularTiempoRestante(LocalTime tiempo){
            LocalTime horaActual = LocalTime.now();
            Duration diferencia = Duration.between(horaActual, tiempo);
            long diferenciaSegundos = diferencia.getSeconds();

            return diferenciaSegundos;
        }

        @Override
        public void run() {
            System.out.println(notificacion.crearMensaje());
            //adapter.enviar(notificacion.getDestinatario(), notificacion.crearMensaje());
        }
    }

}





