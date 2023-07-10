package dominio.servicios;

import java.time.LocalDate;
import java.time.LocalTime;

public class Incidente {

    private String nombreIcidente;
    private Servicio servicioIncidente;
    private String observaciones;
    private LocalDate fechaIncidente;
    private LocalTime horarioIncidente;
    private Boolean estadoIncidente;
    private Miembro miembroNotificador;

    public Incidente(String nombreincidente, Servicio servicioIncidente,
                     String observaciones,
                     LocalTime horarioIncidente,
                     Boolean estadoIncidente,
                     Miembro miembroNotificador) {
        this.nombreIcidente = nombreincidente;
        this.servicioIncidente = servicioIncidente;
        this.observaciones = observaciones;
        this.fechaIncidente = LocalDate.now();
        this.horarioIncidente = horarioIncidente;
        this.estadoIncidente = estadoIncidente;
        this.miembroNotificador = miembroNotificador;
    }

    public Boolean getEstadoIncidente(){
        return this.estadoIncidente;
    }

    public void cerrarIncidente(){
        this.estadoIncidente = false;
    }

    public Miembro getMiembroNotificador() {
        return this.miembroNotificador;
    }

    public Servicio getServicioIncidente() {
        return this.servicioIncidente;
    }

    public String getNombreIcidente(){
        return this.nombreIcidente;
    }

    // hacer el calculo para saber si el incidente es de hoy o dentro de las 24 horas
    public Boolean incidenteActual(){
        // todo
        return true;
    }
}
