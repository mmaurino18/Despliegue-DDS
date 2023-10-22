package controllers;

import dominio.dataBase.repositorios.CiudadanoRepository;
import dominio.dataBase.repositorios.IncidenteRepository;
import dominio.dataBase.repositorios.ServicioRepository;
import dominio.dataBase.repositorios.UsuarioRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
