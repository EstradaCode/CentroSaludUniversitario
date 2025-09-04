package Servicios.Negocio;

import Modelo.OrganizacionSocial;
import Persistencia.OrganizacionSocialDao;
import Persistencia.OrganizacionSocialDaoImpl;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
//final no es compatible con inject, ya que utiliza la reflexión en runtime, cosa que es contraria a final, donde ya debe estár inicializada o añadida en el constructor.
public class OrganizacionSocialService {
    @Inject
    private OrganizacionSocialDao organizacionDao;

    public void guardarOrganizacion(OrganizacionSocial organizacion) {
        organizacionDao.save(organizacion);
    }

    public OrganizacionSocial buscarPorId(Long id) {
        return organizacionDao.findById(id).orElseThrow(()-> new NotFoundException("Organizacion social no encontrada"));
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
