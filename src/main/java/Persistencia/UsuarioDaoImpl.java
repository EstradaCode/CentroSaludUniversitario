package Persistencia;

import Dtos.Usuario.ListUsuarioResponseDTO;
import Modelo.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.Optional;

@RequestScoped
public class UsuarioDaoImpl implements UsuarioDao {

    @Inject
    private EntityManager em;
    @Override
    public void save(Usuario entity) { em.persist(entity); }

    @Override
    public Optional<Usuario> findById(Long id) {
        return Optional.ofNullable(em.find(Usuario.class, id));
    }

    @Override
    public List<Usuario> findAll() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    @Override
    public void update(Usuario entity) { em.merge(entity); }

    @Override
    public void delete(Usuario entity) { em.remove(em.contains(entity) ? entity : em.merge(entity)); }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(this::delete);
    }

    @Override
    public boolean existsById(Long id) {
        Long c = em.createQuery("SELECT COUNT(u.id) FROM Usuario u WHERE u.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return c > 0;
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        List<Usuario> r = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :x", Usuario.class)
                .setParameter("x", username)
                .setMaxResults(1)
                .getResultList();
        return r.stream().findFirst();
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        List<Usuario> r = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :x", Usuario.class)
                .setParameter("x", email)
                .setMaxResults(1)
                .getResultList();
        return r.stream().findFirst();
    }

    @Override
    public Optional<Usuario> findByDni(Long dni) {
        List<Usuario> r = em.createQuery("SELECT u FROM Usuario u WHERE u.dni = :x", Usuario.class)
                .setParameter("x", dni)
                .setMaxResults(1)
                .getResultList();
        return r.stream().findFirst();
    }

    @Override
    public boolean existsByUsername(String username) {
        Long c = em.createQuery("SELECT COUNT(u.id) FROM Usuario u WHERE u.username = :x", Long.class)
                .setParameter("x", username)
                .getSingleResult();
        return c > 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        Long c = em.createQuery("SELECT COUNT(u.id) FROM Usuario u WHERE u.email = :x", Long.class)
                .setParameter("x", email)
                .getSingleResult();
        return c > 0;
    }

    @Override
    public boolean existsByDni(Long dni) {
        Long c = em.createQuery("SELECT COUNT(u.id) FROM Usuario u WHERE u.dni = :x", Long.class)
                .setParameter("x", dni)
                .getSingleResult();
        return c > 0;
    }

    @Override
    public Optional<Usuario> findByIdentity(String identity) {
        // intenta email, si no username
        Optional<Usuario> byEmail = findByEmail(identity);
        return byEmail.isPresent() ? byEmail : findByUsername(identity);
    }

    @Override
    public void habilitarUsuario(Long id) {
        em.createQuery("UPDATE Usuario u SET u.enabled = true WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void deshabilitarUsuario(Long id) {
        em.createQuery("UPDATE Usuario u SET u.enabled = false WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<ListUsuarioResponseDTO> findPageToListDTO(String sort, String q, int offset, int size) {
        // Sanitizá 'sort' contra SQL injection (whitelist de columnas)
        String orderBy = switch (sort) {
            case "username" -> "u.username";
            case "email" -> "u.email";
            case "createdAt" -> "u.createdAt";
            default -> "u.id";
        };

        String base = """
          SELECT new com.tu.paquete.dto.ListUsuarioDTO(
              u.id, u.username, u.email, u.rol, u.enabled, u.createdAt
          )
          FROM Usuario u
          WHERE (:q IS NULL OR :q = '' OR
                 LOWER(u.username) LIKE LOWER(CONCAT('%', :q, '%')) OR
                 LOWER(u.email) LIKE LOWER(CONCAT('%', :q, '%')))
          ORDER BY %s
        """.formatted(orderBy);

        return em.createQuery(base, ListUsuarioResponseDTO.class)
                .setParameter("q", q)
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public long count(String q) {
        Long c = em.createQuery("""
            SELECT COUNT(u.id)
            FROM Usuario u
            WHERE (:q IS NULL OR :q = '' OR
                   LOWER(u.username) LIKE LOWER(CONCAT('%', :q, '%')) OR
                   LOWER(u.email) LIKE LOWER(CONCAT('%', :q, '%')))
        """, Long.class)
                .setParameter("q", q)
                .getSingleResult();
        return c;
    }

}

