package controllers;

import dominio.dataBase.repositorios.IncidenteRepository;
import dominio.dataBase.repositorios.ServicioRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryController {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
    public static EntityManager em = emf.createEntityManager();
    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Servicios": controller = new ServiciosController(new ServicioRepository(em)); break;
            case "Incidentes": controller = new IncidentesController(new IncidenteRepository(em));break;
        }
        return controller;
    }
}
