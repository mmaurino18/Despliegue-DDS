package dominio;

import dominio.comunidad.Comunidad;
import dominio.comunidad.FormaDeNotificacion;
import dominio.comunidad.MedioDeNotificaion;
import dominio.comunidad.Miembro;
import dominio.entidades.Establecimiento;
import dominio.entidades.Estacion;
import dominio.entidades.Sucursal;
import dominio.lectorCSV.CSV;
import dominio.servicios.PrestacionDeServicio;

import java.io.IOException;


public class Pruevas {

    public static void main(String[] args) throws IOException {

        Miembro juan = new Miembro("juan", "ejemplo@gmail.com","123", FormaDeNotificacion.SINAPUROS, MedioDeNotificaion.MAIL,
                0,0);
        Miembro jose = new Miembro("jose", "ejemplo@gmail.com","123", FormaDeNotificacion.SINAPUROS, MedioDeNotificaion.MAIL,
                19,34);
        Miembro alicia = new Miembro("alicia", "ejemplo@gmail.com","123", FormaDeNotificacion.SINAPUROS, MedioDeNotificaion.MAIL,
                19,35);

        Comunidad unaComunidad = new Comunidad();

        unaComunidad.agregarMiembros(juan);
        unaComunidad.agregarMiembros(jose);
        unaComunidad.agregarMiembros(alicia);

        juan.agregarComunidad(unaComunidad);
        jose.agregarComunidad(unaComunidad);
        //alicia.agregarComunidad(unaComunidad);

        Establecimiento estacionRetiro = new Estacion("Estacion Retiro");
        Establecimiento sucursarAlmagro = new Sucursal("Sucursal Almagro");

        PrestacionDeServicio escaleraMecanica = new PrestacionDeServicio("Escalera Mecanica" ,estacionRetiro);
        PrestacionDeServicio banio = new PrestacionDeServicio("banio", sucursarAlmagro);

        // escalera mecanica de estacion retiro - servicio dependiente
        //System.out.println(escaleraMecanica.informacionDeServicioPrestado());

        juan.reportarIncidente(unaComunidad, escaleraMecanica, "corte de luz");
        juan.reportarIncidente(unaComunidad, banio, "se acabo el papel");
        //alicia.reportarIncidente(unaComunidad, banio, "se acabo el papel");

        //alicia.cerrarIncidente(unaComunidad,"corte de luz");
        //juan.cerrarIncidente(unaComunidad,"corte de luz");
        //juan.cerrarIncidente(unaComunidad,"se acabo el papel");

        unaComunidad.getNotificador().iniciarEnvioAsincronico();

        try {
            Thread.sleep(4 * 60 * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        unaComunidad.getNotificador().cerrarServicio();
    }

}
