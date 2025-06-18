package Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityMgmt {

    private static EntityMgmt instance;
    private EntityManagerFactory emf;
    private EntityManager em;

    // Constructor privado para Singleton
    private EntityMgmt() {
        emf = Persistence.createEntityManagerFactory("UNLP_PU"); // le asigné nuestra persistence unit
        em = emf.createEntityManager();
    }

    // Obtener instancia Singleton
    public static synchronized EntityMgmt getInstance() {
        if (instance == null) {
            instance = new EntityMgmt();
        }
        return instance;
    }

    // Obtener EntityManager (el mismo para toda la app)
    public EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
        return em;
    }

    // Abrir transacción
    public void beginTransaction() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    // Commit transacción
    public void commitTransaction() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    // Rollback transacción
    public void rollbackTransaction() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

    // Cerrar EntityManager y EntityManagerFactory
    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}

