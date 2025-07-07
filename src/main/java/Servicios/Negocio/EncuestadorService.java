package Servicios.Negocio;

import Modelo.Encuestador;
import Persistencia.EncuestadorDao;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class EncuestadorService {
    @Inject
    private EncuestadorDao encuestadorDao;

    public void crearEncuestador(Encuestador encuestador) {
        encuestadorDao.save(encuestador);
    }
    public Encuestador buscarEncuestadorPorId(Long id) {
        return encuestadorDao.findById(id);
    }
    public List<Encuestador> listarEncuestadores() {
        return encuestadorDao.findAll();
    }
    public void actualizarEncuestador(Encuestador encuestador) {
        encuestadorDao.update(encuestador);
    }
    public void eliminarEncuestadorPorId(Long id) {
        encuestadorDao.deleteById(id);
    }
    public void eliminarEncuestador(Encuestador encuestador) {
        encuestadorDao.delete(encuestador);
    }

}
