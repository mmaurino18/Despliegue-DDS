package dominio.notificaciones;

import dominio.actores.Ciudadano;
import dominio.servicios.Incidente;

import java.util.ArrayList;
import java.util.List;

public class Notificacion {

    private Ciudadano destinatario;
    private List<Incidente> incidentesYaNotificados;
    private String mensaje;

    public Notificacion (Ciudadano destinatario, Incidente incidente){
        this.destinatario = destinatario;
        this.incidentesYaNotificados = new ArrayList<>();
        this.incidentesYaNotificados.add(incidente);
        this.mensaje = destinatario.getNombre() + " -- ";
    }

    public void agregarIncidente(Incidente incidente){
        this.incidentesYaNotificados.add(incidente);
    }

    public void agregarMensaje(String mensajeAdicional){
        mensaje += mensajeAdicional;
    }

    public Boolean incidenteYaRegistrado(Incidente incidente){
        return this.incidentesYaNotificados.
                stream().
                anyMatch(e -> e.getNombreIcidente().equals(incidente.getNombreIcidente()));
        //return this.incidentesYaNotificados.contains(incidente);
    }

    public void evaluarIncidenteParaNotificar(Incidente incidente){
        if(! this.incidenteYaRegistrado(incidente) && incidente.incidenteActual() ){
            agregarIncidente(incidente);
        }
    }

    // "ServicioPrestado + estado -> (con incidentes, reestablecido)"
    public String crearMensaje(){
        for (Incidente incidente : this.incidentesYaNotificados){
            this.agregarMensaje(incidente.getServicioIncidente().informacionDeServicioPrestado());
            this.agregarMensaje(" " + incidente.estadoIncidente());
            this.agregarMensaje(" -- ");
        }
        return this.mensaje;
    }

    public Ciudadano getDestinatario(){
        return this.destinatario;
    }

}
