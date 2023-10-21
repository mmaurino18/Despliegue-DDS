package dominio.dataBase.repositorios;

import dominio.actores.Ciudadano;
import dominio.servicios.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CiudadanoRepository implements Repository<Ciudadano>, WithSimplePersistenceUnit {

    public CiudadanoRepository() {
    }

    @Override
    public List<Ciudadano> findAll() {
        return entityManager().createQuery("FROM " + Ciudadano.class.getName()).getResultList();
    }
    @Override
    public Ciudadano findById(Long id) {
        return entityManager().find(Ciudadano.class, id);
    }

    @Override
    public void save(Ciudadano ciudadano) {
        EntityTransaction tx = entityManager().getTransaction();

        if(!tx.isActive())
            tx.begin();
        if (ciudadano.getId() == null)
            entityManager().persist(ciudadano);
        else
            entityManager().merge(ciudadano);

        tx.commit();
    }

    @Override
    public void update(Ciudadano ciudadano){
        EntityTransaction tx = entityManager().getTransaction();

        if(!tx.isActive())
            tx.begin();

        entityManager().merge(ciudadano);
        tx.commit();
    }

    @Override
    public void delete(Ciudadano ciudadano) {
        EntityTransaction tx = entityManager().getTransaction();
        if(!tx.isActive())
            tx.begin();

        entityManager().remove(ciudadano);
        tx.commit();
    }

}
