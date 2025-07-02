package Servicios.Negocio;

import Modelo.Barrio;
import Persistencia.BarrioDao;
import Persistencia.BarrioDaoImpl;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BarrioService {
    @Inject
    private BarrioDao barrioDao;

    public void guardarBarrio(Barrio barrio) {
        barrioDao.save(barrio);
    }


    public Barrio buscarPorId(Long id) {
        return barrioDao.findById(id);
    }


    public List<Barrio> buscarTodos() {
        return barrioDao.findAll();
    }

    public List<Barrio> buscarPorNombre(String nombre) {
        return barrioDao.findByName(nombre);
    }


    public void actualizarBarrio(Barrio barrio) {
        barrioDao.update(barrio);
    }

    public void eliminarBarrio(Barrio barrio) {
        barrioDao.delete(barrio);
    }

    public void eliminarPorId(Long id) {
        barrioDao.deleteById(id);
    }

    public Long contarBarrios() {
        return barrioDao.countNeighborhoods();
    }
}

