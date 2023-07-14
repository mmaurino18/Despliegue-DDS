package dominio.servicios;

import dominio.entidades.Establecimiento;

public class PrestacionDeServicio {

    private String nombreServicioPrestado;
    private Servicio servicioPrestado;
    private Establecimiento establecimiento;

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
}
