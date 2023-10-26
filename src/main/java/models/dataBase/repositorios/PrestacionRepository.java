package models.dominio.dataBase.repositorios;

import models.dominio.servicios.PrestacionDeServicio;

import javax.persistence.EntityManager;
import java.util.List;

public class PrestacionRepository implements Repository<PrestacionDeServicio> {

    private final EntityManager em;

    public PrestacionRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(PrestacionDeServicio prestacionDeServicio) {
        if (prestacionDeServicio.getId() == null)
            em.persist(prestacionDeServicio);
        else
            em.merge(prestacionDeServicio);
    }

    @Override
    public List<PrestacionDeServicio> findAll() {
        return em.createQuery("FROM prestacion_de_servicio",PrestacionDeServicio.class).getResultList();
    }

    @Override
    public PrestacionDeServicio findById(Long id) {
        return em.find(PrestacionDeServicio.class,id);
    }

    @Override
    public void delete(PrestacionDeServicio prestacionDeServicio) {

    }
    @Override
    public void update(PrestacionDeServicio prestacionDeServicio){}
}
