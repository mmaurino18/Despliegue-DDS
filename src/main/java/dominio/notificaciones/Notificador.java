package dominio.notificaciones;

import dominio.notificaciones.adapter.NotificadorAdapter;
import dominio.servicios.FormaDeNotificacion;
import dominio.servicios.Incidente;
import dominio.servicios.Miembro;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Notificador {

    HashMap<Miembro,Tarea> notificacionesPorEnviar;
    NotificadorAdapter adapter;
    ScheduledExecutorService servicioPlanificador = Executors.newScheduledThreadPool(0);

    // contructor
    public Notificador(){
        notificacionesPorEnviar = new HashMap<>();
    }

    public void notificar(Notificacion notificacion) {
        adapter.enviar(notificacion.getDestinatario(), notificacion.crearMensaje());
    }

    public void notificarAlInstante(Miembro persona, Incidente incidente){
        Notificacion nuevaNotificacion = new Notificacion(persona, incidente.getNombreIcidente()); // se agregara mas cosas a la notificacion
        this.notificar(nuevaNotificacion);
    }

    public void notificarMiembroSegunSuForma(Miembro persona, Incidente incidente){
        if (persona.getFormadenotificacion() == FormaDeNotificacion.CUANDOSUCEDEN){
            this.notificarAlInstante(persona,incidente);
        }
        if(persona.getFormadenotificacion() == FormaDeNotificacion.SINAPUROS){
            miembroDisponibleParaNotificar(persona, incidente);
            this.cerrarServicio(); // fijarse luego
        }
    }

    public void miembroDisponibleParaNotificar(Miembro miembro, Incidente incidente){
        if( miembro.getHorarioDeNotificaion().compareTo(LocalTime.now()) > 0 ){
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
            Notificacion nuevaNotificacion = new Notificacion(persona, incidente.getNombreIcidente());
            Tarea tarea = new Tarea(nuevaNotificacion);
            servicioPlanificador.schedule(tarea, tarea.calcularTiempoRestante(persona.getHorarioDeNotificaion()), TimeUnit.SECONDS);
            notificacionesPorEnviar.put(persona,tarea);
        }
    }

    public void cerrarServicio(){
        this.servicioPlanificador.shutdown();
    }

    // clase que nada mas la usaremos aca
    static class Tarea implements Runnable {
        private Notificacion notificacion;
        private NotificadorAdapter adapter;

        public Tarea(Notificacion notificacion) {
            this.notificacion = notificacion;
        }

        public void evaluarIncidente(Incidente incidente) {
            notificacion.evaluarIncidenteParaNotificar(incidente.getNombreIcidente());
        }

        public long calcularTiempoRestante(LocalTime tiempo){
            LocalTime horaActual = LocalTime.now(); // hora actual

            long diferenciaHoras = horaActual.until(tiempo, ChronoUnit.HOURS); // Diferencia en horas
            long diferenciaMinutos = horaActual.until(tiempo, ChronoUnit.MINUTES); // Diferencia en minutos
            long diferenciaSegundos = horaActual.until(tiempo, ChronoUnit.SECONDS); // Diferencia en segundos

            long diferenciaTotalSegundos = (diferenciaHoras * 3600) + (diferenciaMinutos * 60) + diferenciaSegundos;

            return diferenciaTotalSegundos;
        }

        @Override
        public void run() {
            adapter.enviar(notificacion.getDestinatario(), notificacion.crearMensaje());
        }
    }


}





