package dominio.dataBase.repositorios;

import dominio.entidades.Establecimiento;

import javax.persistence.EntityManager;
import java.util.List;

public class EstablecimientoRepository implements Repository<Establecimiento> {

    private final EntityManager em;

    public EstablecimientoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Establecimiento establecimiento) {
        if (establecimiento.getId() == null)
            em.persist(establecimiento);
        else
            em.merge(establecimiento);
    }

    @Override
    public List<Establecimiento> findAll() {
        return em.createQuery("FROM establecimiento", Establecimiento.class).getResultList();
    }

    @Override
    public Establecimiento findById(Long id) {
        return em.find(Establecimiento.class,id);
    }

    @Override
    public void delete(Establecimiento establecimiento) {

    }
}
