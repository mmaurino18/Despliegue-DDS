package dominio;

import controllers.FactoryController;
import controllers.ServiciosController;
import dominio.comunidad.Comunidad;
import dominio.comunidad.CuandoNotificar;
import dominio.comunidad.MedioDeNotificaion;
import dominio.actores.Ciudadano;
import dominio.dataBase.repositorios.Repository;
import dominio.dataBase.repositorios.ServicioRepository;
import dominio.entidades.Establecimiento;
import dominio.entidades.Estacion;
import dominio.entidades.Sucursal;
import dominio.notificaciones.Notificador;
import dominio.servicios.PrestacionDeServicio;

import dominio.servicios.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.Javalin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Pruebas implements WithSimplePersistenceUnit{

    public static void main(String[] args) {

        //new Pruebas().transaccion();
        //new Pruebas().testJavalin();
        new Pruebas().testRepositorio();
       // new Pruebas().testController();

    }

    private void testController(){
        ServiciosController controller = (ServiciosController) FactoryController.controller("Servicios");
        controller.test();
    }

    private void testRepositorio(){
        ServicioRepository repositorio = new ServicioRepository();

        /*
        Servicio ascensor = new Servicio();
        ascensor.setNombre("ascensor");
        ascensor.setDescripcion("Aparato elevador que sirve para transportar personas en un edificio");

        repositorio.save(ascensor);
        */

        List<Servicio> servicios = repositorio.findAll();

        for (Servicio elemento : servicios) {
            System.out.println("servicio => " + elemento.getNombre());
            System.out.println("descripcion => " + elemento.getDescripcion());
        }

    }

    private void transaccion(){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        // accion
        tx.commit();
    }

    private void testNotificador(){
        Ciudadano juan = new Ciudadano("juan", "ejemplo@gmail.com","123", CuandoNotificar.SINAPUROS, MedioDeNotificaion.MAIL,
                0,0);
        Ciudadano jose = new Ciudadano("jose", "ejemplo@gmail.com","123", CuandoNotificar.SINAPUROS, MedioDeNotificaion.MAIL,
                13,5);
        Ciudadano alicia = new Ciudadano("alicia", "ejemplo@gmail.com","123", CuandoNotificar.SINAPUROS, MedioDeNotificaion.MAIL,
                13,6);

        Notificador unNotificador = new Notificador();
        Comunidad unaComunidad = new Comunidad(unNotificador);

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
        juan.reportarIncidente(unaComunidad, banio, "se acabo el papel");
        //alicia.reportarIncidente(unaComunidad, banio, "se acabo el papel");

        //alicia.cerrarIncidente(unaComunidad,"corte de luz");
        //juan.cerrarIncidente(unaComunidad,"corte de luz");
        //juan.cerrarIncidente(unaComunidad,"se acabo el papel");

        unNotificador.iniciarEnvioAsincronico();

        try {
            Thread.sleep(4 * 60 * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        unNotificador.cerrarServicio();
    }

    private void testJavalin(){
        Integer port = Integer.parseInt(System.getProperty("port", "8080"));

        Javalin app = Javalin.create().start(port);
        // Javalin app = Javalin.create(config()).start(port);

        app.get("/", ctx -> ctx.result("Hola Mundo"));
    }

}
