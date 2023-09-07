package dominio.comunidad;

import dominio.notificaciones.Notificador;
import dominio.servicios.Incidente;

import java.util.ArrayList;
import java.util.List;

public class Comunidad {

    public String nombre;
    private List<Incidente> incidentesOcurridos;
    private List<Ciudadano> ciudadanos;
    private Notificador notificador;

    public Comunidad(){
        this.incidentesOcurridos = new ArrayList<>();
        this.ciudadanos = new ArrayList<>();
        this.notificador = new Notificador();
    }

    public void reportarIncidente(Incidente nuevoIncidente, Ciudadano miembroAvisante) {
        this.agregarIncidente(nuevoIncidente);
        this.notificarMiembros(nuevoIncidente, miembroAvisante);
    }

    public void cerrarIncidente(String nombreIncidente, Ciudadano miembroAvisante){
        Incidente incidentEncontrado = this.buscarIncidente(nombreIncidente);
        incidentEncontrado.cerrarIncidente();
        this.notificarMiembros(incidentEncontrado, miembroAvisante);
    }

    public void agregarIncidente(Incidente incidente){
        this.incidentesOcurridos.add(incidente);
    }

    public void agregarMiembros(Ciudadano ciudadano){
        this.ciudadanos.add(ciudadano);
    }

    public void notificarMiembros(Incidente incidente, Ciudadano miembroAvisante){
        for (Ciudadano miembro : this.ciudadanos){
            if( miembro != miembroAvisante ){
                this.notificador.notificarMiembroSegunSuForma(miembro, incidente);
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

    public Notificador getNotificador() {
        return notificador;
    }
}
