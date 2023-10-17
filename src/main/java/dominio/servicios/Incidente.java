package dominio.servicios;

import dominio.dataBase.Persistente;
import dominio.entidades.Establecimiento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private String nombreIcidente;

    @ManyToOne
    @JoinColumn(name = "prestacionDeServicio_id",referencedColumnName = "id")
    private PrestacionDeServicio prestacionDeServicioIncidente;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Transient
    private LocalDate fechaIncidente;

    @Transient
    private LocalTime horarioIncidente;

    @Transient
    private Boolean estadoIncidente;

    public Incidente(){

    }

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

    public Boolean incidenteActual(){
        long horastranscurridad = this.horarioIncidente.until(LocalTime.now(), ChronoUnit.HOURS);
        if (horastranscurridad < 24 && fechaIncidente.equals(LocalDate.now())){
            return true;
        }
        else {
            return false;
        }
    }

    public String fechaHora (){
        return LocalDateTime.of(fechaIncidente,horarioIncidente).toString();
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
