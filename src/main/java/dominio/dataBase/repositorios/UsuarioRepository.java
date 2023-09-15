package dominio.dataBase.repositorios;

import dominio.actores.Usuario;

import javax.persistence.EntityManager;
import java.util.List;

public class UsuarioRepository implements Repository<Usuario>{

    private final EntityManager em;

    public UsuarioRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public void save(Usuario usuario) {
        if (usuario.getId() == null)
            em.persist(usuario);
        else
            em.merge(usuario);
    }

    @Override
    public List<Usuario> findAll() {

        List<Usuario> usuarios = em.createQuery("FROM usuario",Usuario.class).getResultList();
        return usuarios;
    }

    @Override
    public Usuario findById(Long id) {

        return em.find(Usuario.class, id);
    }

    @Override
    public void delete(Usuario usuario) {

    }
}
