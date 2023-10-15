package dominio.dataBase.repositorios;

import dominio.actores.Usuario;
import dominio.entidades.EntidadPrestadora;

import javax.persistence.EntityManager;
import java.util.List;

public class EntidadPrestadoraRepository implements Repository<EntidadPrestadora> {

    private final EntityManager em;

    public EntidadPrestadoraRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(EntidadPrestadora entidadPrestadora) {
        if (entidadPrestadora.getId() == null)
            em.persist(entidadPrestadora);
        else
            em.merge(entidadPrestadora);
    }

    @Override
    public List<EntidadPrestadora> findAll() {
        return em.createQuery("FROM entidad_prestadora", EntidadPrestadora.class).getResultList();
    }

    @Override
    public EntidadPrestadora findById(Long id) {
        return em.find(EntidadPrestadora.class,id);
    }

    @Override
    public void delete(EntidadPrestadora entidadPrestadora) {

    }
    @Override
    public void update(EntidadPrestadora entidadPrestadora){}
}
