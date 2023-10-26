package models.dataBase.repositorios;

import models.dominio.comunidad.Comunidad;

import javax.persistence.EntityManager;
import java.util.List;

public class ComunidadRepository implements Repository<Comunidad> {

    private final EntityManager em;

    public ComunidadRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Comunidad comunidad) {
        if (comunidad.getId() == null)
            em.persist(comunidad);
        else
            em.merge(comunidad);
    }

    @Override
    public List<Comunidad> findAll() {
        return em.createQuery("FROM comunidad", Comunidad.class).getResultList();
    }

    @Override
    public Comunidad findById(Long id) {
        return em.find(Comunidad.class,id);
    }

    @Override
    public void delete(Comunidad comunidad) {

    }
    @Override
    public void update(Comunidad comunidad){}
}
