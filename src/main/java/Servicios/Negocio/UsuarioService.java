package Servicios.Negocio;

import Modelo.Usuario;
import Persistencia.UsuarioDao;
import Persistencia.UsuarioDaoImpl;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jvnet.hk2.annotations.Service;

import java.util.List;
@Service
public class UsuarioService {
    @Inject
    private UsuarioDao usuarioDao;

    public void crearUsuario(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioDao.findById(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDao.findAll();
    }

    public void actualizarUsuario(Usuario usuario) {
        usuarioDao.update(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioDao.deleteById(id);
    }

    public Usuario buscarPorUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioDao.findByEmail(email);
    }
    public Usuario buscarPorDni(Long dni) {
        return usuarioDao.findByDni(dni);
    }
}

