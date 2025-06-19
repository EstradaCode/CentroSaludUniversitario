package Persistencia;

import Modelo.Campania;
import Modelo.Jornada;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class JornadaDaoImpl implements JornadaDao{
    // Implementación de los métodos de JornadaDao
    private final jakarta.persistence.EntityManager em;
    public JornadaDaoImpl(jakarta.persistence.EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Jornada jornada) {
        // Lógica para guardar una jornada
    }

    @Override
    public Jornada findById(Long id) {
        // Lógica para encontrar una jornada por ID
        return null; // Reemplazar con la lógica real
    }
    public List<Jornada> findByDate(String date) {
        String jpql = "SELECT j FROM Jornada j WHERE :date = j.date";
        TypedQuery<Jornada> query = em.createQuery(jpql, Jornada.class);
        query.setParameter("date", date);
        return query.getResultList();

    }

    @Override
    public List<Jornada> findAll() {
        // Lógica para encontrar todas las jornadas
        return null; // Reemplazar con la lógica real
    }

    @Override
    public void update(Jornada jornada) {
        // Lógica para actualizar una jornada
    }

    @Override
    public void delete(Jornada jornada) {
        // Lógica para eliminar una jornada por entidad
    }

    @Override
    public void deleteById(Long id) {
        // Lógica para eliminar una jornada por ID
    }
}
