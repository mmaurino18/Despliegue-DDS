package dominio.dataBase.repositorios;

import dominio.actores.Permiso;
import dominio.entidades.EntidadPrestadora;

import javax.persistence.EntityManager;
import java.util.List;

public class PermisoRepository implements Repository<Permiso> {

    private final EntityManager em;

    public PermisoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Permiso permiso) {
        if (permiso.getId() == null)
            em.persist(permiso);
        else
            em.merge(permiso);
    }

    @Override
    public List<Permiso> findAll() {
        return em.createQuery("FROM permiso",Permiso.class).getResultList();
    }

    @Override
    public Permiso findById(Long id) {
        return em.find(Permiso.class,id);
    }

    @Override
    public void delete(Permiso permiso) {

    }
    @Override
    public void update(Permiso permiso){}
}
