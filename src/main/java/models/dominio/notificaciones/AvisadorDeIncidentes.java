package models.dominio.notificaciones;

import models.dominio.actores.Ciudadano;
import models.dominio.servicios.Incidente;

import java.util.List;

public class AvisadorDeIncidentes {
    public List<Ciudadano> ciudadanos;
    public Notificador notificador;

    public AvisadorDeIncidentes() {
    }

    public void notificarIncidentes(Incidente incidente, Notificador notificador){
        for (Ciudadano ciudadano1 : this.ciudadanos) {
            if(!ciudadano1.getIncidentesReportados().contains(incidente)){
                notificador.notificarMiembroSegunSuForma(ciudadano1, incidente);
            }
        }
    }

    public void avisarApertura(Incidente incidente){
        this.notificarIncidentes(incidente, notificador);
    }
    public void avisarCierre(Incidente incidente){
        this.notificarIncidentes(incidente,notificador);
    }

    public void setCiudadanos(List<Ciudadano> ciudadanos) {
        this.ciudadanos = ciudadanos;
    }

    public void setNotificador(Notificador notificador){
        this.notificador = notificador;
    }
}
