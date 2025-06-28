package Persistencia;

import Modelo.Usuario;

public interface UsuarioDao extends GenericDao <Usuario>{
        Usuario findByUsername(String username);
        Usuario findByEmail(String email);
        Usuario findByDni(Long dni);
}
