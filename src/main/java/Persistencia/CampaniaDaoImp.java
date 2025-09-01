package Persistencia;

import Dtos.Campania.ListCampaniaResponseDTO;
import Modelo.Campania;


import java.time.LocalDate;
import java.util.List;


import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import jakarta.persistence.EntityTransaction;

import java.util.Optional;

public class CampaniaDaoImp implements CampaniaDao {

    @Inject
    private EntityManager em;

    @Override
    public void save(Campania entity) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entity);
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        }
    }

    // En CampaniaDaoImp
    public List<ListCampaniaResponseDTO> findPageToListDTO(String sort, String q, int offset, int size) {
        // whitelist de sort
        String orderBy = switch (sort) {
            case "nombre"        -> "c.nombre";
            case "activa"        -> "c.activa";
            case "fechaInicio"   -> "c.fechaInicio";
            case "fechaFin"      -> "c.fechaFin";
            case "barrioNombre"  -> "b.nombre";
            case "organizacion"  -> "o.nombre";
            default              -> "c.fechaInicio DESC";
        };

        String jpql = """
      SELECT new com.tu.paquete.dto.ListCampaniaResponseDTO(
          c.id,
          c.nombre,
          c.activa,
          c.fechaInicio,
          c.fechaFin,
          SIZE(c.jornadas),                                                  /* int */
          (SELECT COUNT(e) FROM Encuesta e WHERE e.jornada.campania.id = c.id), /* long */
          SIZE(c.encuestadores),                                            /* int */
          b.nombre,
          o.nombre
      )
      FROM Campania c
      LEFT JOIN c.barrio b
      LEFT JOIN c.organizacionSocial o
      WHERE (:q IS NULL OR :q = '' OR
             LOWER(c.nombre) LIKE LOWER(CONCAT('%', :q, '%')) OR
             LOWER(b.nombre) LIKE LOWER(CONCAT('%', :q, '%')) OR
             LOWER(o.nombre) LIKE LOWER(CONCAT('%', :q, '%')))
      ORDER BY %s
    """.formatted(orderBy);

        return em.createQuery(jpql, ListCampaniaResponseDTO.class)
                .setParameter("q", q)
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();
    }

    public Long count(String q) {
        String jpql = """
      SELECT COUNT(c)
      FROM Campania c
      LEFT JOIN c.barrio b
      LEFT JOIN c.organizacionSocial o
      WHERE (:q IS NULL OR :q = '' OR
             LOWER(c.nombre) LIKE LOWER(CONCAT('%', :q, '%')) OR
             LOWER(b.nombre) LIKE LOWER(CONCAT('%', :q, '%')) OR
             LOWER(o.nombre) LIKE LOWER(CONCAT('%', :q, '%')))
    """;
        return em.createQuery(jpql, Long.class)
                .setParameter("q", q)
                .getSingleResult();
    }

    @Override
    public Optional<Campania> findById(Long id) {
        return Optional.ofNullable(em.find(Campania.class, id));
    }

    @Override
    public List<Campania> findAll() {
        return em.createQuery("SELECT c FROM Campania c ORDER BY c.fechaInicio DESC", Campania.class)
                .getResultList();
    }

    @Override
    public void update(Campania entity) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(entity);
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        }
    }

    @Override
    public void delete(Campania entity) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        }
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(this::delete);
    }

    @Override
    public List<Campania> findActiveCampaigns(LocalDate date) {
        // Considera campañas abiertas (fechaFin NULL) como activas
        String jpql = """
            SELECT c
            FROM Campania c
            WHERE c.fechaInicio <= :d
              AND (c.fechaFin IS NULL OR :d <= c.fechaFin)
            """;
        TypedQuery<Campania> query = em.createQuery(jpql, Campania.class);
        query.setParameter("d", date);
        return query.getResultList();
    }

    @Override
    public List<Campania> findByName(String partialName) {
        String jpql = """
            SELECT c
            FROM Campania c
            WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :name, '%'))
            """;
        return em.createQuery(jpql, Campania.class)
                .setParameter("name", partialName)
                .getResultList();
    }

    @Override
    public List<Campania> findByOrganization(Long organizationId) {
        // Ajustado a c.organizacion (no organizacionSocial)
        String jpql = "SELECT c FROM Campania c WHERE c.organizacion.id = :orgId";
        return em.createQuery(jpql, Campania.class)
                .setParameter("orgId", organizationId)
                .getResultList();
    }

    @Override
    public List<Campania> findByNeighborhood(Long neighborhoodId) {
        // Adaptado al modelo: Campania -> Zona -> Barrio
        // Si tu Campania tiene ManyToOne Zona (c.zona), filtramos por BARRIO:
        String jpql = """
            SELECT c
            FROM Campania c
            JOIN c.zona z
            JOIN z.barrio b
            WHERE b.id = :barrioId
            """;
        return em.createQuery(jpql, Campania.class)
                .setParameter("barrioId", neighborhoodId)
                .getResultList();
    }

    @Override
    public List<Campania> findWithSurveyors() {
        // Mantengo la consulta tal cual si existe la relación c.encuestadores
        // (si no existe en tu Entity, quitá este método o ajustalo al modelo real)
        String jpql = "SELECT DISTINCT c FROM Campania c JOIN c.encuestadores e";
        return em.createQuery(jpql, Campania.class).getResultList();
    }

    @Override
    public Long countCampaigns() {
        String jpql = "SELECT COUNT(c) FROM Campania c";
        return em.createQuery(jpql, Long.class).getSingleResult();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}


