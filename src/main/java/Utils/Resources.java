package Utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class Resources {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("UNLP_PU");

    @Produces
    public EntityManager produceEntityManager() {
        return emf.createEntityManager();
    }
}