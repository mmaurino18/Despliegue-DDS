package dominio.comunidad;

import com.fasterxml.jackson.annotation.JsonProperty;
import dominio.actores.Ciudadano;
import dominio.dataBase.Persistente;
import dominio.entidades.Establecimiento;
import dominio.notificaciones.Notificador;
import dominio.servicios.Incidente;
import dominio.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comunidad")
@Getter
@Setter

public class Comunidad extends Persistente {


    @Column(name = "nombre")
    @JsonProperty("nombre")
    public String nombre;

    @Transient
    private List<Incidente> incidentesOcurridos;

    @ManyToMany(mappedBy = "comunidades")
    @JsonProperty("miembros")
    private List<Ciudadano> miembros;

    @Transient
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
    @JsonProperty("establecimientos")
    public List<Establecimiento> obtenerEstablecimientosObservados(){
        List<Establecimiento> establecimientos = new ArrayList<>();
        this.incidentesOcurridos.forEach(incidente -> establecimientos.add(incidente.getEstablecimiento()));
        return establecimientos;
    }
    @JsonProperty("servicios")
    public List<Servicio> obtenerServiciosObservados(){
        List<Servicio> servicios = new ArrayList<>();
        this.incidentesOcurridos.forEach(incidente -> servicios.add(incidente.getServicio()));
        return servicios;
    }
}
