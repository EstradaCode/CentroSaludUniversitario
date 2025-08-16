package Persistencia;

import Dtos.Usuario.ListUsuarioDTO;
import Modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioDao extends GenericDao <Usuario, Long>{
                Optional<Usuario> findByUsername(String username);
                Optional<Usuario> findByEmail(String email);
                Optional<Usuario> findByDni(Long dni);

                boolean existsByUsername(String username);
                boolean existsByEmail(String email);
                boolean existsByDni(Long dni);

                // útil para login: email o username
                Optional<Usuario> findByIdentity(String identity);

                // Listado performante
                List<ListUsuarioDTO> findPageToListDTO(String sort, String q, int offset, int size);
                long count(String q);

                // Habilitar / deshabilitar (update directo)
                void habilitarUsuario(Long id);
                void deshabilitarUsuario(Long id);
        }

