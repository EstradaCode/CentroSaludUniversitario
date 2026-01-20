package Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.util.List;

enum Roles {
    ADMIN,
    SALUD,
    VISITANTE
}

@Schema(description = "Usuario del sistema con credenciales, rol y datos personales.")
@Entity
public class Usuario extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del usuario", example = "1")
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(description = "Nombre de usuario único", example = "ludmi123", required = true)
    private String username;

    @Column(nullable = false)
    @Schema(description = "Contraseña del usuario (no debe mostrarse en respuestas)", example = "secreta123", writeOnly = true)
    private String password;

    @Column(nullable = false, unique = true)
    @Schema(description = "Correo electrónico del usuario", example = "ludmi@example.com", required = true)
    private String email;

    @Schema(description = "Estado del usuario: habilitado (true) o deshabilitado (false)", example = "true")
    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Rol del usuario dentro del sistema", example = "ADMIN")
    private Roles rol;

    @Schema(description = "Matrícula profesional si aplica (solo para usuarios con rol SALUD)", example = "32165")
    private Long matricula;

    public Usuario() {}

    public Usuario(String nombre, String apellido, long dni, long telefono, String username, String password, String email, String rolEnString, Long matricula) {
        super(nombre, apellido, dni, telefono);
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = false;
        this.matricula = matricula;
        switch (rolEnString) {
            case "ADMIN" -> this.rol = Roles.ADMIN;
            case "SALUD" -> this.rol = Roles.SALUD;
            case "VISITANTE" -> this.rol = Roles.VISITANTE;
            default -> throw new IllegalArgumentException("Rol no válido: " + rolEnString);
        }
    }

    // Getters y setters ↓↓↓

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean isEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

    public Roles getRol() { return rol; }
    public void setRol(Roles rol) { this.rol = rol; }

    public Long getMatricula() { return matricula; }
    public void setMatricula(Long matricula) { this.matricula = matricula; }
    public Boolean getApprobed() { return enabled; }

    @Override
    public Long getId() { return id; }

    @Override
    public void setId(Long id) { this.id = id; }
}
