package dominio.comunidad;

import dominio.actores.Ciudadano;
import dominio.notificaciones.Notificador;
import dominio.servicios.Incidente;

import java.util.ArrayList;
import java.util.List;

public class Comunidad {

    public String nombre;
    private List<Incidente> incidentesOcurridos;
    private List<Ciudadano> miembros;
    private Notificador notificador;

    public Comunidad(Notificador notificadorInyectado){
        this.incidentesOcurridos = new ArrayList<>();
        this.miembros = new ArrayList<>();
        this.notificador = notificadorInyectado;
    }

    public void reportarIncidente(Incidente nuevoIncidente, Ciudadano miembroAvisante) {
        this.agregarIncidente(nuevoIncidente);
        this.notificarMiembros(nuevoIncidente, miembroAvisante, notificador);
    }

    public void cerrarIncidente(String nombreIncidente, Ciudadano miembroAvisante){
        Incidente incidentEncontrado = this.buscarIncidente(nombreIncidente);
        incidentEncontrado.cerrarIncidente();
        this.notificarMiembros(incidentEncontrado, miembroAvisante, notificador);
    }

    public void agregarIncidente(Incidente incidente){
        this.incidentesOcurridos.add(incidente);
    }

    public void agregarMiembros(Ciudadano ciudadano){
        this.miembros.add(ciudadano);
    }

    public void notificarMiembros(Incidente incidente, Ciudadano miembroAvisante, Notificador notificador){
        for (Ciudadano miembro : this.miembros){
            if( miembro != miembroAvisante ){
                notificador.notificarMiembroSegunSuForma(miembro, incidente);
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
