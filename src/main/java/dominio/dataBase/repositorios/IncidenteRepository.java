package dominio.dataBase.repositorios;

import dominio.entidades.EntidadPrestadora;
import dominio.servicios.Incidente;
import dominio.servicios.Servicio;

import javax.persistence.EntityManager;
import java.util.List;

public class IncidenteRepository implements Repository<Incidente> {

    private final EntityManager em;

    public IncidenteRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Incidente incidente) {
        if (incidente.getId() == null)
            em.persist(incidente);
        else
            em.merge(incidente);
    }

    @Override
    public List<Incidente> findAll() {
        return em.createQuery("FROM incidente",Incidente.class).getResultList();
    }

    @Override
    public Incidente findById(Long id) {
        return em.find(Incidente.class,id);
    }

    @Override
    public void delete(Incidente incidente) {
        if(!em.getTransaction().isActive())
            em.getTransaction().begin();

        em.remove(incidente);
        em.getTransaction().commit();
    }
    @Override
    public void update(Incidente incidente) {
        if(!em.getTransaction().isActive())
            em.getTransaction().begin();

        em.merge(incidente);
        em.getTransaction().commit();
    }
}
