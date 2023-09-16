package dominio.dataBase.repositorios;

import dominio.actores.Ciudadano;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CiudadanoRepository implements Repository<Ciudadano> {

    private final EntityManager em;

    public CiudadanoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Ciudadano ciudadano) {
        if (ciudadano.getId() == null)
            em.persist(ciudadano);
        else
            em.merge(ciudadano);
    }

    @Override
    public List<Ciudadano> findAll() {

        List<Ciudadano> ciudadanos = em.createQuery("from ciudadano", Ciudadano.class).getResultList();
        return ciudadanos;
    }

    @Override
    public Ciudadano findById(Long id) {

        return em.find(Ciudadano.class, id);
    }


    @Override
    public void delete(Ciudadano ciudadano) {

    }
}
