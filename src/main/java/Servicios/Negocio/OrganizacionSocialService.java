package Servicios.Negocio;

import Modelo.OrganizacionSocial;
import Persistencia.OrganizacionSocialDao;
import Persistencia.OrganizacionSocialDaoImpl;
import jakarta.persistence.EntityManager;

import java.util.List;

public class OrganizacionSocialService {
    private final OrganizacionSocialDao organizacionDao;

    public OrganizacionSocialService(EntityManager em) {
        this.organizacionDao = new OrganizacionSocialDaoImpl(em);
    }

    public void guardarOrganizacion(OrganizacionSocial organizacion) {
        organizacionDao.save(organizacion);
    }

    public OrganizacionSocial buscarPorId(Long id) {
        return organizacionDao.findById(id);
    }

    public List<OrganizacionSocial> buscarPorBarrio(Long barrioId) {
        return organizacionDao.findByBarrioId(barrioId);
    }


    public List<OrganizacionSocial> buscarPorNombre(String nombre) {
        return organizacionDao.findByName(nombre);
    }


    public List<OrganizacionSocial> buscarPorDomicilio(String domicilio) {
        return organizacionDao.findByAddress(domicilio);
    }


    public List<OrganizacionSocial> obtenerTodas() {
        return organizacionDao.findAll();
    }


    public void actualizarOrganizacion(OrganizacionSocial organizacion) {
        organizacionDao.update(organizacion);
    }


    public void eliminarOrganizacion(OrganizacionSocial organizacion) {
        organizacionDao.delete(organizacion);
    }


    public void eliminarPorId(Long id) {
        organizacionDao.deleteById(id);
    }


    public Long contarOrganizaciones() {
        return organizacionDao.countOrganizations();
    }
}
