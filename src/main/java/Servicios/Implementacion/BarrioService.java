package Servicios.Implementacion;

import Modelo.Barrio;
import Persistencia.BarrioDao;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

public class BarrioService {
    @Inject
    private BarrioDao barrioDao;

    public void crearBarrio(Barrio barrio) {
        barrioDao.save(barrio);
    }


    public Barrio buscarPorId(Long id) {
        return barrioDao.findById(id).orElseThrow(() -> new NotFoundException("Barrio no encontrado"));
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

