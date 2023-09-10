package dominio.actores;

import dominio.comunidad.Comunidad;
import dominio.comunidad.CuandoNotificar;
import dominio.comunidad.MedioDeNotificaion;
import dominio.servicios.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Ciudadano {

    private String nombre;
    private String mail;
    private String numeroDeTelefono;
    private String localizacion;
    private List<Comunidad> comunidades;
    private List<PrestacionDeServicio> prestacionDeServicios;
    private CuandoNotificar formadenotificacion;
    private MedioDeNotificaion medioDeNotificaion;
    private LocalTime horarioDeNotificaion;


    public Ciudadano(CuandoNotificar forma, MedioDeNotificaion medio){
        this.comunidades = new ArrayList<>();
        this.prestacionDeServicios = new ArrayList<>();
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
        this.prestacionDeServicios = new ArrayList<>();
        this.comunidades = new ArrayList<>();
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

    public CuandoNotificar getFormadenotificacion() {
        return this.formadenotificacion;
    }

    public MedioDeNotificaion getMedioDeNotificaion(){
        return this.medioDeNotificaion;}

    public LocalTime getHorarioDeNotificaion() {
        return this.horarioDeNotificaion;
    }

    public String getMail() {
        return this.mail;
    }

    public String getNumeroDeTelefono(){
        return this.numeroDeTelefono;
    }

    public void agregarComunidad (Comunidad comunidad){
        this.comunidades.add(comunidad);
    }

    public String getNombre() {
        return this.nombre;
    }

}
