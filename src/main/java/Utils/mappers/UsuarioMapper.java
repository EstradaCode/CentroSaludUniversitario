package Utils.mappers;

import Dtos.Usuario.CreateUsuarioRequestDTO;

import Dtos.Usuario.DetailUsuarioResponseDTO;

import Dtos.Usuario.ListUsuarioResponseDTO;
import Dtos.Usuario.UpdateUsuarioRequestDTO;
import Modelo.Roles;
import Modelo.Usuario;

public final class UsuarioMapper {
    private UsuarioMapper() {}

    // Request -> Entity (crear)
    public static Usuario fromCreate(CreateUsuarioRequestDTO dto) {
        var u = new Usuario();
        u.setNombre(dto.nombre());
        u.setApellido(dto.apellido());
        u.setDni(dto.dni());
        u.setTelefono(dto.telefono());
        u.setUsername(dto.username());
        u.setEmail(dto.email());
        u.setRol(Roles.valueOf(dto.rol()));
        u.setMatricula(dto.matricula());
        u.setEnabled(Boolean.TRUE); // o FALSE si querés activar por flujo aparte
        return u; // password hash se setea en el Service
    }

    // Merge parcial (update): NO pises con nulls
    public static void merge(UpdateUsuarioRequestDTO dto, Usuario u) {
        if (dto.nombre() != null) u.setNombre(dto.nombre());
        if (dto.apellido() != null) u.setApellido(dto.apellido());
        if (dto.dni() != null) u.setDni(dto.dni());
        if (dto.telefono() != null) u.setTelefono(dto.telefono());
        if (dto.username() != null) u.setUsername(dto.username());
        if (dto.email() != null) u.setEmail(dto.email());
        if (dto.enabled() != null) u.setEnabled(dto.enabled());
        if (dto.rol() != null) u.setRol(Roles.valueOf(dto.rol()));
        if (dto.matricula() != null) u.setMatricula(dto.matricula());
        // password se trata en el Service (hash), no acá
    }

    // Entity -> Response (detalle)
    public static DetailUsuarioResponseDTO toDetail(Usuario u) {
        return new DetailUsuarioResponseDTO(
                u.getId(), u.getNombre(), u.getApellido(), u.getDni(), u.getTelefono(),
                u.getUsername(), u.getEmail(), u.isEnabled(), u.getRol().name(),
                u.getMatricula()
        );
    }

    // Entity -> Response (listado)
    public static ListUsuarioResponseDTO toList(Usuario u) {
        return new ListUsuarioResponseDTO(
                u.getId(), u.getUsername(), u.getEmail(),
                u.isEnabled(), u.getRol().name()
        );
    }
}

