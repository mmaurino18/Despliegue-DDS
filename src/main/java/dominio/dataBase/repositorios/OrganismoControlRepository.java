package dominio.dataBase.repositorios;

import dominio.entidades.OrganismoDeControl;

import javax.persistence.EntityManager;
import java.util.List;

public class OrganismoControlRepository implements Repository<OrganismoDeControl> {

    private final EntityManager em;

    public OrganismoControlRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(OrganismoDeControl organismoDeControl) {
        if (organismoDeControl.getId() == null)
            em.persist(organismoDeControl);
        else
            em.merge(organismoDeControl);
    }

    @Override
    public List<OrganismoDeControl> findAll() {
        return em.createQuery("FROM organismo_de_control",OrganismoDeControl.class).getResultList();
    }

    @Override
    public OrganismoDeControl findById(Long id) {
        return em.find(OrganismoDeControl.class,id);
    }

    @Override
    public void delete(OrganismoDeControl organismoDeControl) {

    }
}
