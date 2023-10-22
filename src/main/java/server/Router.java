package server;
import controllers.*;
import dominio.actores.Rol;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {
        //
        Server.app().get("/",ctx -> ctx.result("hola Maquina"));
        Server.app().get("/inicio",ctx -> ctx.render("inicio.hbs"));

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

            // registro
            get("registro", ((RegistroCiudadanoController) FactoryController.controller("registroCiudadano"))::index);
            post("registro", ((RegistroCiudadanoController) FactoryController.controller("registroCiudadano"))::save);

            // Login
            get("login", ((LoginController) FactoryController.controller("login")):: index);
            post("login",((LoginController) FactoryController.controller("login")):: addSesion);


            /*path("servicios/{id}/tareas", () -> {
               // get(((TareasController) FactoryController.controller("Tareas"))::index);
                //TODO
            });*/
        });
    }
}