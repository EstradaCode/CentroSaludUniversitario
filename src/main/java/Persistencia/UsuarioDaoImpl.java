package Persistencia;
import Modelo.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.jvnet.hk2.annotations.Service;

import java.util.List;
@ApplicationScoped // NECESITO ARREGLAR LA CONFIG. AUXILIO.
public class UsuarioDaoImpl implements UsuarioDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Usuario usuario) {
        em.persist(usuario);
    }

    @Override
    public Usuario findById(Long id) {
        return em.find(Usuario.class, id);
    }

    @Override
    public List<Usuario> findAll() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    @Override
    public void update(Usuario usuario) {
        em.merge(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
    }

    @Override
    public void deleteById(Long id) {
        Usuario usuario = findById(id);
        if (usuario != null) {
            delete(usuario);
        }
    }

    @Override
    public Usuario findByUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Usuario findByEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public Usuario findByDni(Long dni) {
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.dni = :dni", Usuario.class)
                    .setParameter("dni", dni)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
