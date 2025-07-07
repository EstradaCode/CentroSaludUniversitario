package Servicios.Negocio;

import Modelo.Zona;
import Persistencia.ZonaDao;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class ZonaService {
    @Inject
    private ZonaDao zonaDao;
    public void crearZona(Zona zona) {
        zonaDao.save(zona);
    }
    public Zona buscarZonaPorId(Long id) {
        return zonaDao.findById(id);
    }
    public List<Zona> buscarZonasPorNombre(String nombre) {
        return zonaDao.findByNombre(nombre);
    }
    public List<Zona> listarZonas() {
        return zonaDao.findAll();
    }
    public void actualizarZona(Zona zona) {
        zonaDao.update(zona);
    }
    public void eliminarZonaPorId(Long id) {
        zonaDao.deleteById(id);
    }
    public void eliminarZona(Zona zona) {
        zonaDao.delete(zona);
    }

}
