package Servicios.Negocio;

import Dtos.Usuario.*;
import Modelo.Usuario;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface IUsuarioService {
    PageResponse<ListUsuarioResponseDTO> listar(int page, int size, String sort, String q);
    DetailUsuarioResponseDTO obtener(Long id);                 // lanza 404 si no existe
    Long crear(CreateUsuarioRequestDTO dto);                  // lanza 409 si duplica
    @Transactional
    void actualizar(Long id, UpdateUsuarioRequestDTO dto);

    void eliminar(Long id);                            // idempotente o 404, a elección

    Optional<Usuario> buscarPorEmailOUsername(String identity);
    boolean verificarPassword(Usuario u, char[] raw);
}

