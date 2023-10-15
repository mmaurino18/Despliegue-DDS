package dominio.dataBase.repositorios;

import dominio.entidades.Entidad;
import dominio.entidades.EntidadPrestadora;

import javax.persistence.EntityManager;
import java.util.List;

public class EntidadRepository implements Repository<Entidad> {

    private final EntityManager em;

    public EntidadRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Entidad entidad) {
        if (entidad.getId() == null)
            em.persist(entidad);
        else
            em.merge(entidad);
    }

    @Override
    public List<Entidad> findAll() {
        return em.createQuery("FROM entidad",Entidad.class).getResultList();
    }

    @Override
    public Entidad findById(Long id) {
        return em.find(Entidad.class,id);
    }

    @Override
    public void delete(Entidad entidad) {

    }
    @Override
    public void update(Entidad entidad){}
}
