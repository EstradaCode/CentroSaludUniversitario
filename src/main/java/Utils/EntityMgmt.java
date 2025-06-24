package Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityMgmt {

    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("UNLP_PU");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager(); // Devuelve una nueva instancia por request
    }

    public static void closeFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}

