package Persistencia;
import Modelo.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import java.util.List;
@RequestScoped // NECESITO ARREGLAR LA CONFIG. AUXILIO.
public class UsuarioDaoImpl implements UsuarioDao {
    @Inject
    private EntityManager em;


    @Override
    @Transactional
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
    @Transactional
    public void update(Usuario usuario) {
        em.merge(usuario);
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
    }

    @Override
    @Transactional
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
    @Override
    @Transactional
    public void habilitarUsuario(Long id) {
        Usuario usuario = findById(id);
        if (usuario != null) {
            usuario.setEnabled(true);
            em.merge(usuario);
        }
    }

    @Override
    @Transactional
    public void deshabilitarUsuario(Long id) {
        Usuario usuario = findById(id);
        if (usuario != null) {
            usuario.setEnabled(false);
            em.merge(usuario);
        }
    }

}
