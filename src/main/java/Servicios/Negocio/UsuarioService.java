package Servicios.Negocio;

import Modelo.Usuario;
import Persistencia.UsuarioDao;
import Persistencia.UsuarioDaoImpl;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UsuarioService {
    private UsuarioDao usuarioDao;

    public UsuarioService(EntityManager em) {
        this.usuarioDao = new UsuarioDaoImpl(em);
    }

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

