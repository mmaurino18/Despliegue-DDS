package server;
import controllers.FactoryController;
import controllers.RegistroCiudadanoController;
import controllers.ServiciosController;
import controllers.IncidentesController;
import dominio.actores.Rol;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {
        Server.app().get("/", ctx -> {
            ctx.sessionAttribute("item1", "Cosa 1");
            ctx.result("Hola mundooooooo");
        });

        Server.app().get("/saluda", ctx -> {
            ctx.result("Hola "
                    + ctx.queryParam("nombre")
                    + ", " + ctx.sessionAttribute("item1")
            );
        });
        Server.app().get("/saludo-para/{nombre}", ctx -> ctx.result("Hola "
                + ctx.pathParam("nombre")
        ));

        // TEST
        Server.app().get("/test",ctx -> ctx.render("test.hbs"));

        //
        Server.app().get("/inicio",ctx -> ctx.render("inicio.hbs"));
        Server.app().get("/registroOk",ctx -> ctx.render("registroOk.hbs"));

        Server.app().routes(() -> {
            get("servicios", ((ServiciosController) FactoryController.controller("Servicios"))::index);
            get("servicios/crear", ((ServiciosController) FactoryController.controller("Servicios"))::create);
            get("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::show);
            get("servicios/{id}/editar", ((ServiciosController) FactoryController.controller("Servicios"))::edit);
            post("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::update);
            post("servicios", ((ServiciosController) FactoryController.controller("Servicios"))::save);
            delete("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::delete);

            get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index);
            get("incidentes/crear", ((IncidentesController) FactoryController.controller("Incidentes"))::create);
            get("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);
            get("incidentes/{id}/editar", ((IncidentesController) FactoryController.controller("Incidentes"))::edit);
            post("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::update);
            post("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::save);
            delete("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::delete);

            get("registro", ((RegistroCiudadanoController) FactoryController.controller("registroCiudadano"))::index);
            post("registro", ((RegistroCiudadanoController) FactoryController.controller("registroCiudadano"))::save);
            //get("registro/crear", ((ServiciosController) FactoryController.controller("Servicios"))::create);
            //get("registro/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::show);
            //get("registro/{id}/editar", ((ServiciosController) FactoryController.controller("Servicios"))::edit);
            //post("registro/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::update);
            //delete("registro/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::delete);

            /*path("servicios/{id}/tareas", () -> {
               // get(((TareasController) FactoryController.controller("Tareas"))::index);
                //TODO
            });*/
        });
    }
}