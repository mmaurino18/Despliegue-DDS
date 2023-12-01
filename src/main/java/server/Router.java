package server;
import controllers.*;
import models.dominio.actores.TipoRol;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {
        //
        Server.app().get("/",ctx -> ctx.render("inicio.hbs"));

        Server.app().get("/test",ctx -> ctx.render("/editP/create_organismoDeControlP.hbs"));

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

            //notificaciones
            get("config-notificacion", ((NotifController) FactoryController.controller("notif"))::index );
            post("config-notificacion",((NotifController) FactoryController.controller("notif"))::save );

            // BEGIN - PROPIETARIO  -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            //home Propietario
            get("homePropietario",((HomePropietarioController) FactoryController.controller("homePropietario")) :: index );

            // Carga Masiva
            get("cargaMasiva",((CargaMasivaController) FactoryController.controller("cargaMasiva"))::index);
            get("cargaMasiva/estructura",((CargaMasivaController) FactoryController.controller("cargaMasiva"))::edit);
            post("cargaMasiva",((CargaMasivaController) FactoryController.controller("cargaMasiva"))::save);

            // organismoDeControlP
            get("organismosDeControlP/Test",((OrganismoDeControlPController) FactoryController.controller("organismosDeControlP"))::indexTest); // TEST

            get("organismosDeControlP",((OrganismoDeControlPController) FactoryController.controller("organismosDeControlP"))::index);
            get("organismosDeControlP/crear", ((OrganismoDeControlPController) FactoryController.controller("organismosDeControlP"))::create);
            get("organismosDeControlP/{id}", ((OrganismoDeControlPController) FactoryController.controller("organismosDeControlP"))::show);
            get("organismosDeControlP/{id}/editar", ((OrganismoDeControlPController) FactoryController.controller("organismosDeControlP"))::edit);
            post("organismosDeControlP/{id}", ((OrganismoDeControlPController) FactoryController.controller("organismosDeControlP"))::update);
            post("organismosDeControlP", ((OrganismoDeControlPController) FactoryController.controller("organismosDeControlP"))::save);
            delete("organismosDeControlP/{id}", ((OrganismoDeControlPController) FactoryController.controller("organismosDeControlP"))::delete);

            // entidadPrestadoraP
            get("entidadesPrestadorasP/Test",((EntidadPrestadoraPController) FactoryController.controller("entidadesPrestadorasP"))::indexTest); // TEST

            get("organismosDeControlP/{idODC}/entidadesPrestadorasP",((EntidadPrestadoraPController) FactoryController.controller("entidadesPrestadorasP"))::index);
            get("organismosDeControlP/{idODC}/entidadesPrestadorasP/crear", ((EntidadPrestadoraPController) FactoryController.controller("entidadesPrestadorasP"))::create);
            get("organismosDeControlP/{idODC}/entidadesPrestadorasP/{id}", ((EntidadPrestadoraPController) FactoryController.controller("entidadesPrestadorasP"))::show);
            get("organismosDeControlP/{idODC}/entidadesPrestadorasP/{id}/editar", ((EntidadPrestadoraPController) FactoryController.controller("entidadesPrestadorasP"))::edit);
            post("organismosDeControlP/{idODC}/entidadesPrestadorasP/{id}", ((EntidadPrestadoraPController) FactoryController.controller("entidadesPrestadorasP"))::update);
            post("organismosDeControlP/{idODC}/entidadesPrestadorasP", ((EntidadPrestadoraPController) FactoryController.controller("entidadesPrestadorasP"))::save);
            delete("organismosDeControlP/{idODC}/entidadesPrestadorasP/{id}", ((EntidadPrestadoraPController) FactoryController.controller("entidadesPrestadorasP"))::delete);

            // entidadP
            get("entidadesP/Test",((EntidadPController) FactoryController.controller("entidadesP"))::indexTest); // TEST

            get("organismosDeControlP/{idODC}/entidadesPrestadorasP/{idEP}/entidadesP",((EntidadPController) FactoryController.controller("entidadesP"))::index);
            get("organismosDeControlP/{idODC}/entidadesPrestadorasP/{idEP}/entidadesP/crear", ((EntidadPController) FactoryController.controller("entidadesP"))::create);
            get("organismosDeControlP/{idODC}/entidadesPrestadorasP/{idEP}/entidadesP/{id}", ((EntidadPController) FactoryController.controller("entidadesP"))::show);
            get("organismosDeControlP/{idODC}/entidadesPrestadorasP/{idEP}/entidadesP/{id}/editar", ((EntidadPController) FactoryController.controller("entidadesP"))::edit);
            post("organismosDeControlP/{idODC}/entidadesPrestadorasP/{idEP}/entidadesP/{id}", ((EntidadPController) FactoryController.controller("entidadesP"))::update);
            post("organismosDeControlP/{idODC}/entidadesPrestadorasP/{idEP}/entidadesP", ((EntidadPController) FactoryController.controller("entidadesP"))::save);
            delete("organismosDeControlP/{idODC}/entidadesPrestadorasP/{idEP}/entidadesP/{id}", ((EntidadPController) FactoryController.controller("entidadesP"))::delete);


            // establecimientosP
            get("establecimientosP/Test",((EstablecimientoPController) FactoryController.controller("establecimientosP"))::indexTest); // TEST

            get("organismosDeControlP/{idODC}/entidadesPrestadorasP/{idEP}/entidadesP/{idE}/establecimientosP",((EstablecimientoPController) FactoryController.controller("establecimientosP"))::index);
            get("organismosDeControlP/{idODC}/entidadesPrestadorasP/{idEP}/entidadesP/{idE}/establecimientosP/crear", ((EstablecimientoPController) FactoryController.controller("establecimientosP"))::create);
            get("organismosDeControlP/{idODC}/entidadesPrestadorasP/{idEP}/entidadesP/{idE}/establecimientosP/{id}", ((EstablecimientoPController) FactoryController.controller("establecimientosP"))::show);
            get("organismosDeControlP/{idODC}/entidadesPrestadorasP/{idEP}/entidadesP/{idE}/establecimientosP/{id}/editar", ((EstablecimientoPController) FactoryController.controller("establecimientosP"))::edit);
            post("organismosDeControlP/{idODC}/entidadesPrestadorasP/{idEP}/entidadesP/{idE}/establecimientosP/{id}", ((EstablecimientoPController) FactoryController.controller("establecimientosP"))::update);
            post("organismosDeControlP/{idODC}/entidadesPrestadorasP/{idEP}/entidadesP/{idE}/establecimientosP", ((EstablecimientoPController) FactoryController.controller("establecimientosP"))::save);
            delete("organismosDeControlP/{idODC}/entidadesPrestadorasP/{idEP}/entidadesP/{idE}/establecimientosP/{id}", ((EstablecimientoPController) FactoryController.controller("establecimientosP"))::delete);

            // localizacionP
            get("localizacion/Tipo",new LocalizacionPController():: index);
            post("localizacion/Tipo",new LocalizacionPController():: tipoLocalizacion);
            post("localizacion/Tipo/{idProvincia}/municipio",new LocalizacionPController() :: mostrarMunicipios);

            // END - PROPIETARIO -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            /*path("servicios/{id}/tareas", () -> {
               // get(((TareasController) FactoryController.controller("Tareas"))::index);
                //TODO
            });*/
        });
    }
}