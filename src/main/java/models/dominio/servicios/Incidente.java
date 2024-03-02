package models.dominio.servicios;

import models.dataBase.Persistente;
import models.dominio.entidades.Establecimiento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "incidente")

@Getter
@Setter
public class Incidente extends Persistente {

    @Column(name = "nombre", columnDefinition = "VARCHAR(55)")
    private String nombreIncidente;

    @ManyToOne
    @JoinColumn(name = "prestacionDeServicio_id",referencedColumnName = "id")
    private PrestacionDeServicio prestacionDeServicioIncidente;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;



    @Column(name = "fechaApertura", columnDefinition = "DATETIME")
    private LocalDateTime fechaApertura;

    @Column(name = "fechaCierre", columnDefinition = "DATETIME", nullable = true)
    private LocalDateTime fechaCierre;

    @Column(name = "estadoIncidente", columnDefinition = "BOOLEAN")
    private Boolean estadoIncidente;

    public Incidente(){
        this.fechaApertura = LocalDateTime.now();
        this.fechaCierre = null;
        this.estadoIncidente = true;
    }

    public Incidente(String nombreincidente,
                     PrestacionDeServicio prestacionDeServicioIncidente,
                     String observaciones,
                     LocalTime horarioIncidente){
        this.nombreIncidente = nombreincidente;
        this.prestacionDeServicioIncidente = prestacionDeServicioIncidente;
        this.observaciones = observaciones;
        this.fechaApertura = LocalDateTime.now();
        this.fechaCierre = null;
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
        this.fechaCierre = LocalDateTime.now();
    }

    public Boolean incidenteActual(){
        return Duration.between(fechaApertura,LocalDateTime.now()).toHours() < 24;
    }

    public String fechaHora (){
        return fechaApertura.toLocalDate().toString() + " - "+ fechaApertura.toLocalTime().toString();
    }
    public long duracionMinutos(){
        if(fechaCierre!= null){
            return Duration.between(fechaApertura,fechaCierre).toMinutes();
        }
        else{
            return 0;
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
