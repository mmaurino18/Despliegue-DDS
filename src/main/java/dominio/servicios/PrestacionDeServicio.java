package dominio.servicios;

import dominio.entidades.Establecimiento;
import lombok.Getter;

public class PrestacionDeServicio {

    private String nombreServicioPrestado;
    @Getter
    private Servicio servicioPrestado;
    @Getter
    private Establecimiento establecimiento;
    private Boolean estadoDeServicioPrestado;

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
        return this.establecimiento.getnombre();
    }

    public String informacionDeServicioPrestado(){
        return (this.nombreServicioPrestado + " de " + this.deEstablecimiento());
    }

    public boolean getEstadoServicioPrestado(){
        return this.estadoDeServicioPrestado;
    }

    public void servicioConIncidentes(){
        this.estadoDeServicioPrestado = false;
    }

    public void reestablecerServicio(){
        this.estadoDeServicioPrestado = true;
    }
}
