package controllers;

import models.dataBase.repositorios.*;

public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Servicios": controller = new ServiciosController(new ServicioRepository());break;
            case "Incidentes": controller = new IncidentesController(new IncidenteRepository(), new PrestacionRepository(), new CiudadanoRepository());break;
            case "registroCiudadano": controller = new RegistroCiudadanoController(new CiudadanoRepository(), new UsuarioRepository(), new PropietarioRepository());break;
            case "login": controller = new LoginController(new UsuarioRepository()); break;
            case "home": controller = new HomeController(); break;
            case "homePropietario": controller = new HomePropietarioController(); break;
            case "notif": controller = new NotifController(new CiudadanoRepository()); break;

            case "Ranking": controller = new RankingController(); break;
            case "cargaMasiva": controller = new CargaMasivaController(new PropietarioRepository()); break;
            case "organismosDeControlP": controller = new OrganismoDeControlPController(new OrganismoControlRepository()); break;
            case "entidadesPrestadorasP": controller = new EntidadPrestadoraPController(new OrganismoControlRepository()); break;
            case "entidadesP": controller = new EntidadPController(new OrganismoControlRepository(), new EntidadPrestadoraRepository()); break;
            case "establecimientosP": controller = new EstablecimientoPController(new EntidadRepository(), new OrganismoControlRepository(), new EntidadPrestadoraRepository()); break;

        }
        return controller;
    }
}
