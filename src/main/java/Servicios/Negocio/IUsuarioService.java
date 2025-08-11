package Servicios.Negocio;

import Dtos.*;
import Dtos.Usuario.*;

public interface IUsuarioService {

    PageResponse<ListUsuarioDTO> listar(int page, int size, String sort, String q);

    DetailUsuarioDTO obtener(Long id);

    Long crear(CreateUsuarioDTO dto);

    boolean actualizar(Long id, UpdateUsuarioDTO dto);

    boolean eliminar(Long id);

    // administración
    boolean actualizarEstado(Long id, boolean enabled);
}
