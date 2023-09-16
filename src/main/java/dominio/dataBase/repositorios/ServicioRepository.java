package dominio.dataBase.repositorios;

import dominio.servicios.Servicio;

import javax.persistence.EntityManager;
import java.util.List;

public class ServicioRepository implements Repository<Servicio> {

    private final EntityManager em;

    public ServicioRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Servicio servicio) {
        if (servicio.getId() == null)
            em.persist(servicio);
        else
            em.merge(servicio);
    }

    @Override
    public List<Servicio> findAll() {
        return em.createQuery("FROM servicio",Servicio.class).getResultList();
    }

    @Override
    public Servicio findById(Long id) {
        return em.find(Servicio.class,id);
    }

    @Override
    public void delete(Servicio servicio) {

    }
}
