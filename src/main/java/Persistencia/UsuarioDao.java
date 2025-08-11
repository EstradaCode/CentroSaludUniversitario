package Persistencia;

import Modelo.Usuario;

import java.util.List;

public interface UsuarioDao extends GenericDao <Usuario>{
        Usuario findByUsername(String username);
        Usuario findByEmail(String email);
        Usuario findByDni(Long dni);
        void habilitarUsuario(Long id);
        void deshabilitarUsuario(Long id);
        List<Usuario> findPage(String sort, String q, int offset, int size);
        long count(String q);
}
