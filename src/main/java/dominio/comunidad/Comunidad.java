package dominio.comunidad;

import dominio.comunidad.Miembro;
import dominio.notificaciones.Notificador;
import dominio.notificaciones.adapter.MailAdapter;
import dominio.notificaciones.adapter.WhatsappAdapter;
import dominio.servicios.Incidente;

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

    public void reportarIncidente(Incidente nuevoIncidente, Miembro miembroAvisante) {
        this.agregarIncidente(nuevoIncidente);
        this.notificarMiembros(nuevoIncidente, miembroAvisante);
    }

    public void cerrarIncidente(String nombreIncidente, Miembro miembroAvisante){
        Incidente incidentEncontrado = this.buscarIncidente(nombreIncidente);
        incidentEncontrado.cerrarIncidente();
        this.notificarMiembros(incidentEncontrado, miembroAvisante);
    }

    public void agregarIncidente(Incidente incidente){
        this.incidentesOcurridos.add(incidente);
    }

    public void agregarMiembros(Miembro miembro){
        this.miembros.add(miembro);
    }

    public void notificarMiembros(Incidente incidente, Miembro miembroAvisante){
        for (Miembro miembro : this.miembros){
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
