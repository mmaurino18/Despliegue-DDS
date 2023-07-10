package dominio.servicios;

import dominio.notificaciones.Notificador;

import java.util.ArrayList;
import java.util.List;

public class Comunidad {

    private List<Incidente> incidentesOcurridos;
    private List<Miembro> miembros;
    private Notificador notificador;

    public Comunidad(){
        this.incidentesOcurridos = new ArrayList<>();
        this.miembros = new ArrayList<>();
        this.notificador = new Notificador();
    }

    public void reportarIncidente(Incidente nuevoIncidente) {
        this.agregarIncidente(nuevoIncidente);
        this.notificarMiembros(nuevoIncidente);
    }

    public void cerrarIncidente(String nombreIncidente){
        Incidente incidentEncontrado = this.buscarIncidente(nombreIncidente);
        incidentEncontrado.cerrarIncidente();
        this.notificarMiembros(incidentEncontrado);
    }

    public void agregarIncidente(Incidente incidente){
        this.incidentesOcurridos.add(incidente);
    }

    public void notificarMiembros(Incidente incidente){
        for (Miembro persona : this.miembros){
            if( !( persona == incidente.getMiembroNotificador() ) ){
                this.notificador.notificarMiembroSegunSuForma(persona, incidente);
            }
        }
    }

    public Incidente buscarIncidente(String nombreIncidente){
        Incidente incidenteEncontrado = null;
        for (Incidente incidente : this.incidentesOcurridos) {
            if ( incidente.getNombreIcidente().equals(nombreIncidente) && incidente.getEstadoIncidente() ) {
                incidenteEncontrado = incidente;
                break;
            }
        }
        return incidenteEncontrado;
    }



}
