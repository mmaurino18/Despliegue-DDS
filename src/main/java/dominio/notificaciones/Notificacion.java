package dominio.notificaciones;

import dominio.servicios.Miembro;

import java.util.ArrayList;
import java.util.List;

public class Notificacion {

    private Miembro Destinatario;
    private String correoDestinatario;
    private List<String> incidentesYaNotificados;
    private String asunto;
    private String mensaje;

    public Notificacion (Miembro destinatario, String nombreIncidente){
        this.Destinatario = destinatario;
        this.incidentesYaNotificados = new ArrayList<>();
        this.incidentesYaNotificados.add(nombreIncidente);
    }


    public void agregarIncidente(String incidente){
        this.incidentesYaNotificados.add(incidente);
    }

    public void agregarMensaje(String mensajeAdicional){
        mensaje += mensajeAdicional;
    }

    public Boolean incidenteYaRegistrado( String nombreIncidente ){
        return this.incidentesYaNotificados.contains(nombreIncidente);
    }

    public void evaluarIncidenteParaNotificar(String nombreIncidente){
        if(! this.incidenteYaRegistrado(nombreIncidente) ){
            agregarIncidente(nombreIncidente);
        }
    }



}
