package server;
import controllers.*;
import models.dominio.actores.TipoRol;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {
        //
        Server.app().get("/",ctx -> ctx.render("inicio.hbs"));

        Server.app().routes(() -> {
            get("servicios", ((ServiciosController) FactoryController.controller("Servicios"))::index,TipoRol.CIUDADANO);
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
            post("incidentes/crear", ((IncidentesController) FactoryController.controller("Incidentes"))::save);
            post("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::update);
            post("ranking", ((RankingController) FactoryController.controller("Ranking"))::show);
            get("ranking", ((RankingController) FactoryController.controller("Ranking"))::index);
            get("cerrarIncidente/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::delete);
            // registro
            get("registro", ((RegistroCiudadanoController) FactoryController.controller("registroCiudadano"))::index);
            post("registro", ((RegistroCiudadanoController) FactoryController.controller("registroCiudadano"))::save);

            // Login
            get("login", ((LoginController) FactoryController.controller("login")):: index);
            post("login",((LoginController) FactoryController.controller("login")):: addSesion);

            //home
            get("home",((HomeController) FactoryController.controller("home")) :: index );

            //home Propietario
            get("homePropietario",((HomePropietarioController) FactoryController.controller("homePropietario")) :: index );

            //notificaciones
            get("config-notificacion", ((NotifController) FactoryController.controller("notif"))::index );
            post("config-notificacion",((NotifController) FactoryController.controller("notif"))::save );

            // Carga Masiva
            get("cargaMasiva",((CargaMasivaController) FactoryController.controller("cargaMasiva"))::index);
            get("cargaMasiva/estructura",((CargaMasivaController) FactoryController.controller("cargaMasiva"))::edit);
            post("cargaMasiva",((CargaMasivaController) FactoryController.controller("cargaMasiva"))::save);

            /*path("servicios/{id}/tareas", () -> {
               // get(((TareasController) FactoryController.controller("Tareas"))::index);
                //TODO
            });*/
        });
    }
}