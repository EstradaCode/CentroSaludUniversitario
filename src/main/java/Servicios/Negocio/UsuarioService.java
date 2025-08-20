package Servicios.Negocio;

import Dtos.Usuario.*;
import Modelo.Roles;
import Modelo.Usuario;
import Persistencia.UsuarioDao;
import Utils.mappers.UsuarioMapper;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;

import java.util.List;
import java.util.Optional;

@RequestScoped
public class UsuarioService implements IUsuarioService {

    @Inject UsuarioDao usuarioDao;
    //@Inject PasswordService passwordService; // Argon2 o bcrypt

    @Override
    public PageResponse<ListUsuarioDTO> listar(int page, int size, String sort, String q) {
        if (size <= 0) size = 20;
        if (size > 100) size = 100;
        if (page < 0) page = 0;

        int offset = page * size;

        // ✅ Proyección directa desde el DAO (más performante)
        List<ListUsuarioDTO> content = usuarioDao.findPageToListDTO(sort, q, offset, size);
        long total = usuarioDao.count(q);

        return new PageResponse<>(content, total, page, size);
    }

    @Override
    public DetailUsuarioDTO obtener(Long id) {
        Usuario u = usuarioDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        return UsuarioMapper.toDetailDTO(u);
    }

    @Override
    @Transactional
    public Long crear(CreateUsuarioDTO dto) {
        // ✅ Unicidad → 409 Conflict (mejor que 400)
        if (usuarioDao.existsByEmail(dto.getEmail())) {
            throw new WebApplicationException("El email ya está registrado", 409);
        }
        if (usuarioDao.existsByUsername(dto.getUsername())) {
            throw new WebApplicationException("El username ya está registrado", 409);
        }

        Usuario u = UsuarioMapper.fromCreateDTO(dto);

        // Defaults seguros
        if (u.getRol() == null) u.setRol(Roles.VISITANTE);
        if (u.getEnabled() == null) u.setEnabled(false);

        // ✅ Hash SIEMPRE acá (no aceptar hashes del cliente)
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new BadRequestException("Password requerido");
        }
        //u.setPassword(passwordService.hash(dto.getPassword().toCharArray()));

        usuarioDao.save(u);
        return u.getId();
    }

    @Transactional
    @Override
    public void actualizar(Long id, UpdateUsuarioDTO dto) {
        Usuario u = usuarioDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        // Unicidad si cambian email/username
        if (dto.getEmail() != null && !dto.getEmail().equalsIgnoreCase(u.getEmail())
                && usuarioDao.existsByEmail(dto.getEmail())) {
            throw new WebApplicationException("El email ya está registrado", 409);
        }
        if (dto.getUsername() != null && !dto.getUsername().equalsIgnoreCase(u.getUsername())
                && usuarioDao.existsByUsername(dto.getUsername())) {
            throw new WebApplicationException("El username ya está registrado", 409);
        }

        UsuarioMapper.updateEntityFromDTO(dto, u);

        // Password opcional: si viene, validar + hash
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            if (dto.getPassword().length() < 8) {
                throw new BadRequestException("La contraseña debe tener al menos 8 caracteres");
            }
            //u.setPassword(passwordService.hash(dto.getPassword().toCharArray()));
        }

        usuarioDao.update(u);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        // Podés hacerlo idempotente: deleteById sin check, o lanzar 404 si no existe
        if (!usuarioDao.existsById(id)) {
            throw new NotFoundException("Usuario no encontrado");
        }
        usuarioDao.deleteById(id);
    }

    // ---- soporte login ----
    @Override
    public Optional<Usuario> buscarPorEmailOUsername(String identity) {
        return usuarioDao.findByIdentity(identity);
    }

    @Override
    public boolean verificarPassword(Usuario u, char[] raw) {
        //return passwordService.verify(u.getPassword(), raw);
        return true;
    }
}


