package dominio.servicios;

import dominio.dataBase.Persistente;
import dominio.entidades.Establecimiento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "prestacion_de_servicio")
@Getter
@Setter
public class PrestacionDeServicio extends Persistente {

    @Column(name = "nombre", columnDefinition = "VARCHAR(55)")
    private String nombreServicioPrestado;

    @ManyToOne
    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
    private Servicio servicioPrestado;

    @ManyToOne
    @JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
    private Establecimiento establecimiento;

    @Transient
    private Boolean estadoDeServicioPrestado;

    public PrestacionDeServicio(){
        // para Hibernate
    }

    // test
    public PrestacionDeServicio(String nombreServicioPrestado, Establecimiento establecimiento){
        this.nombreServicioPrestado = nombreServicioPrestado;
        this.establecimiento = establecimiento;
    }

    public PrestacionDeServicio(String nombreServicioPrestado, Servicio servicioPrestado, Establecimiento establecimiento) {
        this.nombreServicioPrestado = nombreServicioPrestado;
        this.servicioPrestado = servicioPrestado;
        this.establecimiento = establecimiento;
    }

    public void prestarServicio(){
        // todo
    }


    public String deEstablecimiento(){
        return this.establecimiento.getNombre();
    }

    public String informacionDeServicioPrestado(){
        return (this.nombreServicioPrestado + " de " + this.deEstablecimiento());
    }

    public void servicioConIncidentes(){
        this.estadoDeServicioPrestado = false;
    }

    public void reestablecerServicio(){
        this.estadoDeServicioPrestado = true;
    }
}
