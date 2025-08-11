package Utils.mappers;

import Dtos.Usuario.CreateUsuarioDTO;
import Dtos.Usuario.DetailUsuarioDTO;
import Dtos.Usuario.ListUsuarioDTO;
import Dtos.Usuario.UpdateUsuarioDTO;
import Modelo.Roles;
import Modelo.Usuario;

public class UsuarioMapper {

    public static ListUsuarioDTO toListDTO(Usuario usuario) {
        ListUsuarioDTO dto = new ListUsuarioDTO();
        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername());
        dto.setEmail(usuario.getEmail());
        dto.setEnabled(usuario.isEnabled());
        dto.setRol(usuario.getRol().toString());
        return dto;
    }

    public static DetailUsuarioDTO toDetailDTO(Usuario usuario) {
        DetailUsuarioDTO dto = new DetailUsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setDni(usuario.getDni());
        dto.setTelefono(usuario.getTelefono());
        dto.setUsername(usuario.getUsername());
        dto.setEmail(usuario.getEmail());
        dto.setEnabled(usuario.isEnabled());
        dto.setRol(usuario.getRol().name());
        dto.setMatricula(usuario.getMatricula());
        return dto;
    }

    public static Usuario fromCreateDTO(CreateUsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setDni(dto.getDni());
        usuario.setTelefono(dto.getTelefono());
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(Roles.valueOf(dto.getRol()));
        usuario.setMatricula(dto.getMatricula());
        usuario.setEnabled(true);
        return usuario;
    }

    public static void updateEntityFromDTO(UpdateUsuarioDTO dto, Usuario usuario) {
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setDni(dto.getDni());
        usuario.setTelefono(dto.getTelefono());
        usuario.setUsername(dto.getUsername());
        usuario.setEmail(dto.getEmail());
        usuario.setEnabled(dto.getEnabled());
        usuario.setRol(Roles.valueOf(dto.getRol()));
        usuario.setMatricula(dto.getMatricula());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            usuario.setPassword(dto.getPassword());
        }
    }
}
