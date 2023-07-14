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
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Pruevas {

    public static void main(String[] args) throws IOException {
        
        Miembro juan = new Miembro("juan", "ejemplo@gmail.com", FormaDeNotificacion.SINAPUROS, MedioDeNotificaion.MAIL,
                0,0);
        Miembro jose = new Miembro("jose", "ejemplo@gmail.com", FormaDeNotificacion.SINAPUROS, MedioDeNotificaion.MAIL,
                7,30 );
        Miembro alicia = new Miembro("alicia", "ejemplo@gmail.com", FormaDeNotificacion.SINAPUROS, MedioDeNotificaion.MAIL,
                0,0);

        Comunidad unaComunidad = new Comunidad();

        unaComunidad.agregarMiembros(juan);
        unaComunidad.agregarMiembros(jose);
        unaComunidad.agregarMiembros(alicia);

        juan.agregarComunidad(unaComunidad);
        jose.agregarComunidad(unaComunidad);
        alicia.agregarComunidad(unaComunidad);

        Establecimiento estacionRetiro = new Estacion("Estacion Retiro");
        Establecimiento sucursarAlmagro = new Sucursal("Sucursal Almagro");

        PrestacionDeServicio escaleraMecanica = new PrestacionDeServicio("Escalera Mecanica" ,estacionRetiro);
        PrestacionDeServicio banio = new PrestacionDeServicio("banio", sucursarAlmagro);

        // escalera mecanica de estacion retiro - servicio dependiente
        //System.out.println(escaleraMecanica.informacionDeServicioPrestado());

        juan.reportarIncidente(unaComunidad, escaleraMecanica, "corte de luz");
        jose.reportarIncidente(unaComunidad, banio, "se acabo el papel");

        juan.cerrarIncidente(unaComunidad,"corte de luz");
        jose.cerrarIncidente(unaComunidad, "se acabo el papel");

        unaComunidad.getNotificador().getServicioPlanificador().shutdown();

    }

}
