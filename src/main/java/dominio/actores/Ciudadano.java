package dominio.actores;

import com.fasterxml.jackson.annotation.JsonProperty;
import dominio.api.Localizacion;
import dominio.comunidad.Comunidad;
import dominio.comunidad.CuandoNotificar;
import dominio.comunidad.MedioDeNotificaion;
import dominio.dataBase.Persistente;
import dominio.servicios.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ciudadano")
@Getter
@Setter
public class Ciudadano extends Persistente {
    @JsonProperty("nombre")
    @Column(name = "nombre", columnDefinition = "VARCHAR(55)")
    private String nombre;

    @Column(name = "numeroDeTelefono", columnDefinition = "VARCHAR(55)")
    private String numeroDeTelefono;

    @Column(name = "mail", columnDefinition = "VARCHAR(55)")
    private String mail;

    // unidireccional
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToMany
    private List<Comunidad> comunidades;

    @Transient
    private CuandoNotificar formadenotificacion;
    @Transient
    private MedioDeNotificaion medioDeNotificaion;
    @Transient
    private LocalTime horarioDeNotificaion;
    @Transient
    private List<PrestacionDeServicio> interes;
    @Transient
    private List<Incidente> incidentesReportados;
    @Transient
    private Localizacion localizacionDeInteres;
    @Transient
    private Ubicacion ubicacion;


    public Ciudadano (){
        this.comunidades = new ArrayList<>();
        this.interes = new ArrayList<>();
        this.incidentesReportados = new ArrayList<>();
    }

    public Ciudadano(CuandoNotificar forma, MedioDeNotificaion medio){
        this.comunidades = new ArrayList<>();
        this.interes = new ArrayList<>();
        this.incidentesReportados = new ArrayList<>();
        this.formadenotificacion = forma;
        this.medioDeNotificaion = medio;
    }

    // test
    public Ciudadano(String nombre, String mail, String numero, CuandoNotificar forma, MedioDeNotificaion medio, int hora, int minuto){
        this.nombre = nombre;
        this.mail = mail;
        this.numeroDeTelefono = numero;
        this.formadenotificacion = forma;
        this.medioDeNotificaion = medio;
        this.horarioDeNotificaion = LocalTime.of(hora,minuto);
        this.interes = new ArrayList<>();
        this.comunidades = new ArrayList<>();
        this.incidentesReportados = new ArrayList<>();
    }

    public void reportarIncidente(Comunidad comunidad, PrestacionDeServicio prestacionDeServicio, String nombreIncidente){
        Incidente nuevoIncidente = new Incidente(
                nombreIncidente,
                prestacionDeServicio,
                "observaciones",
                LocalTime.now());
        nuevoIncidente.abrirIncidente();
        comunidad.reportarIncidente(nuevoIncidente, this);
    }

    // sera una busqueda, luego preguntar, por el momento asumo que se pasa
    // el nombre del incidente y si esta en la lista de la comunidad se le cambia
    // el estado a cerrado (por el momento representado con un false)
    public void cerrarIncidente(Comunidad comunidad, String nombreIncidente){
       comunidad.cerrarIncidente(nombreIncidente,this);
    }

    public void estadoDeIcidente(Comunidad comunidad, Incidente incidente){
        // todo
    }

    public void agregarComunidad(Comunidad unacomunidad){
        this.comunidades.add(unacomunidad);
        unacomunidad.agregarMiembros(this); //
    }



}
