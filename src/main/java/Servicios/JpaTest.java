package Servicios;
import Modelo.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UNLP_PU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Persona persona = new Persona("Juan", "Perez", 12345678L, 1122334455L);
            em.persist(persona);

            em.getTransaction().commit();

            System.out.println("Persona persistida con ID: " + persona.getId());
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
