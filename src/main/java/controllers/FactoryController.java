package controllers;

import models.dataBase.repositorios.CiudadanoRepository;
import models.dataBase.repositorios.IncidenteRepository;
import models.dataBase.repositorios.ServicioRepository;
import models.dataBase.repositorios.UsuarioRepository;

public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Servicios": controller = new ServiciosController(new ServicioRepository());break;
            case "Incidentes": controller = new IncidentesController(new IncidenteRepository());break;
            case "registroCiudadano": controller = new RegistroCiudadanoController(new CiudadanoRepository());break;
            case "login": controller = new LoginController(new UsuarioRepository()); break;
        }
        return controller;
    }
}
