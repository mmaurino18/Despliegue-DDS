package dominio.servicios;

import dominio.entidades.Establecimiento;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Incidente {

    private String nombreIcidente;
    private PrestacionDeServicio prestacionDeServicioIncidente;
    private String observaciones;
    private LocalDate fechaIncidente;
    private LocalTime horarioIncidente;
    private Boolean estadoIncidente;

    public Incidente(String nombreincidente,
                     PrestacionDeServicio prestacionDeServicioIncidente,
                     String observaciones,
                     LocalTime horarioIncidente){
        this.nombreIcidente = nombreincidente;
        this.prestacionDeServicioIncidente = prestacionDeServicioIncidente;
        this.observaciones = observaciones;
        this.fechaIncidente = LocalDate.now();
        this.horarioIncidente = horarioIncidente;
    }

    public Boolean getEstadoIncidente(){
        return this.estadoIncidente;
    }

    public void abrirIncidente(){
        this.estadoIncidente = true;
        this.prestacionDeServicioIncidente.servicioConIncidentes();
    }

    public void cerrarIncidente(){
        this.estadoIncidente = false;
        this.prestacionDeServicioIncidente.reestablecerServicio();
    }

    public PrestacionDeServicio getServicioIncidente() {
        return this.prestacionDeServicioIncidente;
    }


    public String getNombreIcidente(){
        return this.nombreIcidente;
    }

    public Boolean incidenteActual(){
        long horastranscurridad = this.horarioIncidente.until(LocalTime.now(), ChronoUnit.HOURS);
        if (horastranscurridad < 24 && fechaIncidente.equals(LocalDate.now())){
            return true;
        }
        else {
            return false;
        }
    }

    public String estadoIncidente (){
        if (this.estadoIncidente){
            return "Con Incidentes";
        }
        else {
            return "tuvo Incidentes pero fue Reestablecido";
        }
    }
    public Servicio getServicio(){
        return this.prestacionDeServicioIncidente.getServicioPrestado();
    }
    public Establecimiento getEstablecimiento(){
        return this.prestacionDeServicioIncidente.getEstablecimiento();
    }
}
