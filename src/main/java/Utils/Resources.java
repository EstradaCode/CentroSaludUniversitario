package Utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class Resources {

    @PersistenceContext(unitName = "UNLP_PU")
    private EntityManager em;

    @Produces
    @RequestScoped // cuando alguien necesite un entitymanager por inyeccion dale este generador para su request
    public EntityManager produceEntityManager() {
        return em;
    }
}