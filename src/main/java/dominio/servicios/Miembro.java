package dominio.servicios;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Miembro {

    private String nombre;
    private String apellido;
    private String mail;
    private int numeroDeTelefono;
    private String localizacion;
    private List<Comunidad> comunidades;
    private List<Servicio> servicios;
    private String interes;
    private FormaDeNotificacion formadenotificacion;
    private MedioDeNotificaion medioDeNotificaion;
    private LocalTime horarioDeNotificaion;


    public Miembro(FormaDeNotificacion forma, MedioDeNotificaion medio){
        this.comunidades = new ArrayList<>();
        this.servicios = new ArrayList<>();
        this.formadenotificacion = forma;
        this.medioDeNotificaion = medio;
    }

    public void reportarIncidente(Comunidad comunidad, Servicio servicio, String nombreIncidente){
        Incidente nuevoIncidente = new Incidente(nombreIncidente, servicio,"observaciones", LocalTime.now(), true, this);
        comunidad.reportarIncidente(nuevoIncidente);
    }

    // sera una busqueda, luego preguntar, por el momento asumo que se pasa
    // le nombre del incidente y si esta en la lista de la comunidad se le cambia
    // el estado a cerrado (por el meomento representado con un false)
    public void cerrarIncidente(Comunidad comunidad, String nombreIncidente){
       comunidad.cerrarIncidente(nombreIncidente);
    }

    public FormaDeNotificacion getFormadenotificacion() {
        return formadenotificacion;
    }

    public LocalTime getHorarioDeNotificaion() {
        return horarioDeNotificaion;
    }
}
