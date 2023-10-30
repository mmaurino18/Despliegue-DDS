package controllers;

import models.dataBase.repositorios.*;

public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Servicios": controller = new ServiciosController(new ServicioRepository());break;
            case "Incidentes": controller = new IncidentesController(new IncidenteRepository(), new PrestacionRepository());break;
            case "registroCiudadano": controller = new RegistroCiudadanoController(new CiudadanoRepository());break;
            case "login": controller = new LoginController(new UsuarioRepository()); break;
            case "home": controller = new HomeController(); break;
        }
        return controller;
    }
}
