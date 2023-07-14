package dominio.notificaciones;

import dominio.comunidad.Miembro;
import dominio.servicios.Incidente;

import java.util.ArrayList;
import java.util.List;

public class Notificacion {

    private Miembro destinatario;
    private List<Incidente> incidentesYaNotificados;
    private String mensaje;

    public Notificacion (Miembro destinatario, Incidente incidente){
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
        return this.incidentesYaNotificados.contains(incidente.getNombreIcidente());
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
            this.agregarMensaje(" --");
            return this.mensaje;
        }
        return null;
    }

    public Miembro getDestinatario(){
        return destinatario;
    }

}
