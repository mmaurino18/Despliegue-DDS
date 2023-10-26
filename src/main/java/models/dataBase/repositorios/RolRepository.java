package models.dominio.dataBase.repositorios;

import models.dominio.actores.Rol;

import javax.persistence.EntityManager;
import java.util.List;

public class RolRepository implements Repository<Rol> {

    private final EntityManager em;

    public RolRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public void save(Rol rol) {
        if (rol.getId() ==null)
            em.persist(rol);
        else
            em.merge(rol);
    }

    @Override
    public List<Rol> findAll() {
        return em.createQuery("FROM rol",Rol.class).getResultList();
    }

    @Override
    public Rol findById(Long id) {
        return em.find(Rol.class,id);
    }

    @Override
    public void delete(Rol rol) {

    }
    @Override
    public void update(Rol rol){}
}
