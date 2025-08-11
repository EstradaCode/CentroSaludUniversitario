package Servicios.Negocio;

import Dtos.Usuario.CreateUsuarioDTO;
import Dtos.Usuario.DetailUsuarioDTO;
import Dtos.Usuario.ListUsuarioDTO;
import Dtos.Usuario.PageResponse;
import Modelo.Roles;
import Modelo.Usuario;
import Persistencia.UsuarioDao;
import Persistencia.UsuarioDaoImpl;
import Utils.mappers.UsuarioMapper;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;

import java.util.List;
@RequestScoped
public class UsuarioService implements IUsuarioService {

    @Inject
    UsuarioDao usuarioDao;

    @Override
    public PageResponse<ListUsuarioDTO> listar(int page, int size, String sort, String q) {
        if (size <= 0) size = 20;
        if (size > 100) size = 100;
        if (page < 0) page = 0;
        int offset = page * size;

        // DAO devuelve ENTIDADES
        List<Usuario> usuarios = usuarioDao.findPage(sort, q, offset, size);
        long total = usuarioDao.count(q);

        // El Service mapea a DTOs
        List<ListUsuarioDTO> content = usuarios.stream()
                .map(UsuarioMapper::toListDTO)
                .toList();

        return new PageResponse<>(content, total, page, size);
    }

    @Override
    public DetailUsuarioDTO obtener(Long id) {
        Usuario u = usuarioDao.findById(id);
        return (u == null) ? null : UsuarioMapper.toDetailDTO(u);
    }

    @Override
    @Transactional
    public Long crear(CreateUsuarioDTO dto) {
        // Validaciones de unicidad básicas (recomendado mapear a 409 con ExceptionMapper)
        if (usuarioDao.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("El email ya está registrado");
        }
        if (usuarioDao.existsByUsername(dto.getUsername())) {
            throw new BadRequestException("El username ya está registrado");
        }

        Usuario u = UsuarioMapper.fromCreateDTO(dto);
        // Defaults seguros (si tu mapper no lo setea):
        if (u.getRol() == null) u.setRol(Roles.VISITANTE);
        if (u.isEnabled() == null) u.setEnabled(false);
        // Hash de password si no lo hace el mapper
       /* if (u.getPassword() != null && !u.getPassword().startsWith("$2a$")) {
            u.setPassword(BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
        } */

        usuarioDao.save(u);
        return u.getId();
    }

    @Override
    @Transactional
    public boolean actualizar(Long id, UsuarioUpdateDTO dto) {
        Usuario u = usuarioDao.findById(id);
        if (u == null) return false;

        // Si se cambia el email/username, validá unicidad
        if (dto.getEmail() != null && !dto.getEmail().equalsIgnoreCase(u.getEmail())
                && usuarioDao.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("El email ya está registrado");
        }
        if (dto.getUsername() != null && !dto.getUsername().equalsIgnoreCase(u.getUsername())
                && usuarioDao.existsByUsername(dto.getUsername())) {
            throw new BadRequestException("El username ya está registrado");
        }

        UsuarioMapper.mergeUpdate(dto, u);

        // Si aceptás password en UpdateDTO (opcional, writeOnly)
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            if (dto.getPassword().length() < 8) {
                throw new BadRequestException("La contraseña debe tener al menos 8 caracteres");
            }
            u.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        }

        usuarioDao.update(u);
        return true;
    }

