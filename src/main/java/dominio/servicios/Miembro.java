package dominio.servicios;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Miembro {

    private String nombre;
    private String apellido;
    private String mail;
    private String numeroDeTelefono;
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

    public String getMail() {
        return mail;
    }
    public String getNumeroDeTelefono(){
        return numeroDeTelefono;
    }

    public static Establecimiento encontrarCoordenadaMasCercana(List<Establecimiento> establecimientos, double x, double y) {
         Establecimiento establecimientoMasCercano = null;
        double distanciaMinima = Double.MAX_VALUE;

        for (Establecimiento establecimiento : establecimientos) {
            double x2= Establecimiento.getCoordenadaX();
            double y2 = Establecimiento.getCoordenaY();
            double distancia = calcularDistancia(x, y,x2,y2);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                establecimientoMasCercano = establecimiento;
            }
        }
        return establecimientoMasCercano;
    }
    public static double calcularDistancia(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}

