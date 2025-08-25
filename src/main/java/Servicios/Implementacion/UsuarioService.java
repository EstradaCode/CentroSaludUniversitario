package Servicios.Implementacion;

import Dtos.Usuario.*;
import Modelo.Roles;
import Modelo.Usuario;
import Persistencia.UsuarioDao;
import Servicios.Negocio.IUsuarioService;
import Utils.Session.PasswordService;
import Utils.mappers.UsuarioMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioService implements IUsuarioService {

    @Inject UsuarioDao usuarioDao;
    @Inject
    PasswordService passwordService; // bcrypt (at.favre)

    @Override
    public PageResponse<ListUsuarioResponseDTO> listar(int page, int size, String sort, String q) {
        if (size <= 0) size = 20;
        if (size > 100) size = 100;
        if (page < 0) page = 0;

        int offset = page * size;

        List<ListUsuarioResponseDTO> content = usuarioDao.findPageToListDTO(sort, q, offset, size);
        long total = usuarioDao.count(q);

        return new PageResponse<>(content, total, page, size);
    }

    @Override
    public DetailUsuarioResponseDTO obtener(Long id) {
        Usuario u = usuarioDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        return UsuarioMapper.toDetail(u);
    }

    @Override
    @Transactional
    public Long crear(CreateUsuarioRequestDTO dto) {
        // 409 por unicidad
        if (usuarioDao.existsByEmail(dto.email())) {
            throw new WebApplicationException("El email ya está registrado", 409);
        }
        if (usuarioDao.existsByUsername(dto.username())) {
            throw new WebApplicationException("El username ya está registrado", 409);
        }

        Usuario u = UsuarioMapper.fromCreate(dto);

        // Defaults seguros por si vinieran nulos
        if (u.getRol() == null) u.setRol(Roles.VISITANTE);
        if (u.isEnabled() == null) u.setEnabled(false);

        // Password requerida y hasheada SIEMPRE en el service
        if (dto.password() == null || dto.password().isBlank()) {
            throw new BadRequestException("Password requerido");
        }
        u.setPassword(passwordService.hash(dto.password().toCharArray()));

        usuarioDao.save(u);
        return u.getId();
    }

    @Transactional
    @Override
    public void actualizar(Long id, UpdateUsuarioRequestDTO dto) {
        Usuario u = usuarioDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        // Unicidad si cambian email/username
        if (dto.email() != null && !dto.email().equalsIgnoreCase(u.getEmail())
                && usuarioDao.existsByEmail(dto.email())) {
            throw new WebApplicationException("El email ya está registrado", 409);
        }
        if (dto.username() != null && !dto.username().equalsIgnoreCase(u.getUsername())
                && usuarioDao.existsByUsername(dto.username())) {
            throw new WebApplicationException("El username ya está registrado", 409);
        }

        // Merge parcial (no pisa con nulls)
        UsuarioMapper.merge(dto, u);

        // Password opcional: si viene, validar + hash
        if (dto.password() != null && !dto.password().isBlank()) {
            if (dto.password().length() < 8) {
                throw new BadRequestException("La contraseña debe tener al menos 8 caracteres");
            }
            u.setPassword(passwordService.hash(dto.password().toCharArray()));
        }

        usuarioDao.update(u);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
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
        return passwordService.verify(u.getPassword(), raw);
    }
}
