package controllers;

import dominio.dataBase.repositorios.IncidenteRepository;
import dominio.dataBase.repositorios.ServicioRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Servicios": controller = new ServiciosController(new ServicioRepository()); break;
            case "Incidentes": controller = new IncidentesController(new IncidenteRepository());break;
        }
        return controller;
    }
}
