package Persistencia;

import Modelo.Encuesta;
import Utils.Enums.AtencionSalud;
import Utils.Enums.TipoVivienda;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityTransaction;

import java.time.*;
import java.util.*;

public class EncuestaDaoImp implements EncuestaDao {

    @Inject
    private EntityManager em;

    @Override
    public void save(Encuesta entity) {
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

    @Override
    public void saveAll(List<Encuesta> entities) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            int i = 0;
            for (Encuesta e : entities) {
                em.persist(e);
                // batch simple para ETL
                if (++i % 50 == 0) {
                    em.flush();
                    em.clear();
                }
            }
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        }
    }

    @Override
    public Optional<Encuesta> findById(Long id) {
        return Optional.ofNullable(em.find(Encuesta.class, id));
    }

    @Override
    public List<Encuesta> findAll() {
        return em.createQuery("SELECT e FROM Encuesta e ORDER BY e.fechaCreacion DESC", Encuesta.class)
                .getResultList();
    }

    @Override
    public void update(Encuesta entity) {
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
    public void delete(Encuesta entity) {
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

    // -------- Adaptados a tu entidad --------

    @Override
    public List<Encuesta> findByFechaCreacion(Date fecha) {
        // Rango [00:00, 24:00) del día de 'fecha' (zona sistema)
        LocalDate ld = Instant.ofEpochMilli(fecha.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        Date start = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end   = Date.from(ld.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        String jpql = """
            SELECT e
            FROM Encuesta e
            WHERE e.fechaCreacion >= :start
              AND e.fechaCreacion <  :end
            ORDER BY e.fechaCreacion DESC
            """;
        return em.createQuery(jpql, Encuesta.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }

    @Override
    public List<Encuesta> findByUuidApi(String uuidApi) {
        // Asegurate de que 'uuidApi' existe en tu Entity Encuesta
        String jpql = "SELECT e FROM Encuesta e WHERE e.uuidApi = :uuidApi";
        return em.createQuery(jpql, Encuesta.class)
                .setParameter("uuidApi", uuidApi)
                .getResultList();
    }

    @Override
    public List<Encuesta> findByTipoVivienda(TipoVivienda tipoVivienda) {
        String jpql = "SELECT e FROM Encuesta e WHERE e.tipoVivienda = :tipo";
        return em.createQuery(jpql, Encuesta.class)
                .setParameter("tipo", tipoVivienda)
                .getResultList();
    }

    @Override
    public Long countByAccesoAgua(Boolean accesoAgua) {
        String jpql = "SELECT COUNT(e) FROM Encuesta e WHERE e.accesoAgua = :v";
        return em.createQuery(jpql, Long.class)
                .setParameter("v", accesoAgua)
                .getSingleResult();
    }

    @Override
    public Long countByAguaPotable(Boolean aguaPotable) {
        String jpql = "SELECT COUNT(e) FROM Encuesta e WHERE e.aguaPotable = :v";
        return em.createQuery(jpql, Long.class)
                .setParameter("v", aguaPotable)
                .getSingleResult();
    }

    // Reemplazo de countByAccesoSalud(Boolean) por set membership real:
    @Override
    public Long countByAtencionSalud(AtencionSalud tipo) {
        String jpql = "SELECT COUNT(e) FROM Encuesta e WHERE :t MEMBER OF e.atencionSalud";
        return em.createQuery(jpql, Long.class)
                .setParameter("t", tipo)
                .getSingleResult();
    }
}

