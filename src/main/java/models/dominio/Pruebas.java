package models.dominio;

import controllers.FactoryController;
import controllers.ServiciosController;
import models.dominio.actores.Propietario;
import models.dominio.actores.Usuario;
import models.dominio.comunidad.Comunidad;
import models.dominio.comunidad.CuandoNotificar;
import models.dominio.comunidad.MedioDeNotificaion;
import models.dominio.actores.Ciudadano;
import models.dataBase.repositorios.ServicioRepository;
import models.dataBase.repositorios.UsuarioRepository;
import models.dominio.lectorCSV.CSV;
import models.dominio.notificaciones.Notificador;

import models.dominio.servicios.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.Javalin;

import javax.persistence.EntityTransaction;
import java.io.IOException;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.get;

public class Pruebas implements WithSimplePersistenceUnit{

    public static void main(String[] args) throws IOException {

        //new Pruebas().testrepoUsuario();
        new Pruebas().transaccion();
        //new Pruebas().testJavalin();
        //new Pruebas().testRepositorio();
       // new Pruebas().testController();
        //new Pruebas().testLectorCSV();

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

       //1 Establecimiento estacionRetiro = new Estacion("Estacion Retiro");
       //1 Establecimiento sucursarAlmagro = new Sucursal("Sucursal Almagro");

        //1 PrestacionDeServicio escaleraMecanica = new PrestacionDeServicio("Escalera Mecanica" ,estacionRetiro);
        //1 PrestacionDeServicio banio = new PrestacionDeServicio("banio", sucursarAlmagro);

        // escalera mecanica de estacion retiro - servicio dependiente
        //System.out.println(escaleraMecanica.informacionDeServicioPrestado());

        //1 juan.reportarIncidente(unaComunidad, escaleraMecanica, "corte de luz");
        //1 juan.reportarIncidente(unaComunidad, banio, "se acabo el papel");
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

        app.get("/", ctx -> ctx.result("Hola Mundo"));
    }

    private void testrepoUsuario(){
        UsuarioRepository repository = new UsuarioRepository();

        Usuario usuario = repository.findByNombre("DDS16");

        System.out.println(usuario.getNombre());
        System.out.println(usuario.getContrasenia());

    }

    private void testLectorCSV() throws IOException {
        CSV lector = new CSV();

        Propietario propietario = new Propietario();

        List<String> lineas = lector.lectorDeCSV("src\\main\\java\\models\\dominio\\archivos\\archivo.csv");

        lector.mapearDatos(lineas,propietario);

        System.out.println(propietario.getOrganismosDeControl().get(0).getNombre());
        System.out.println(propietario.getOrganismosDeControl().get(0).getEntidadesPrestadoras().get(0).getNombre());

    }

}
