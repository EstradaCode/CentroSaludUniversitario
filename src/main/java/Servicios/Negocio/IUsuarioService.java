package Servicios.Negocio;

import Dtos.*;
import Dtos.Usuario.*;
import Modelo.Usuario;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface IUsuarioService {
    PageResponse<ListUsuarioDTO> listar(int page, int size, String sort, String q);
    DetailUsuarioDTO obtener(Long id);                 // lanza 404 si no existe
    Long crear(CreateUsuarioDTO dto);                  // lanza 409 si duplica
    void actualizar(Long id, UpdateUsuarioDTO dto);   // 404 si no existe, 409 si duplica

    @Transactional
    void actualizar(Long id, UsuarioUpdateDTO dto);

    void eliminar(Long id);                            // idempotente o 404, a elección

    Optional<Usuario> buscarPorEmailOUsername(String identity);
    boolean verificarPassword(Usuario u, char[] raw);
}

