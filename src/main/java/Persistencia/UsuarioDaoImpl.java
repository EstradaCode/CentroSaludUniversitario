package Persistencia;

import Modelo.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

@RequestScoped
public class UsuarioDaoImpl implements UsuarioDao {

    @Inject
    private EntityManager em;

    @Override
    public void save(Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
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
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Usuario usuario) {
        em.getTransaction().begin();
        em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
        em.getTransaction().commit();
    }

    @Override
    public void deleteById(Long id) {
        Usuario usuario = findById(id);
        if (usuario != null) {
            delete(usuario); // ya hace begin/commit
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

    @Override
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
    public void habilitarUsuario(Long id) {
        em.getTransaction().begin();
        Usuario usuario = findById(id);
        if (usuario != null) {
            usuario.setEnabled(true);
            em.merge(usuario);
        }
        em.getTransaction().commit();
    }

    @Override
    public void deshabilitarUsuario(Long id) {
        em.getTransaction().begin();
        Usuario usuario = findById(id);
        if (usuario != null) {
            usuario.setEnabled(false);
            em.merge(usuario);
        }
        em.getTransaction().commit();
    }
}

