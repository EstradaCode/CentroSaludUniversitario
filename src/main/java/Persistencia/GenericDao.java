package Persistencia;

import java.util.List;

public interface GenericDao<T> {
    // Crear
    void save(T entity);

    // Leer por Id
    T findById(Long id);

    // Leer todos
    List<T> findAll();

    // Actualizar elems
    void update(T entity);

    // Eliminar por entidad
    void delete(T entity);

    // Eliminar por Id
    void deleteById(Long id);
}
