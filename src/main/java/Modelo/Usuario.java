package Modelo;

import jakarta.persistence.*;

import java.util.List;

enum Roles {
    ADMIN,
    SALUD,
    VISITANTE
}
@Entity
public class Usuario extends Persona{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true) // el user no deberia repetirse
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String email;
    private Boolean isApprobed;
    @Enumerated(EnumType.STRING)
    private Roles rol;
    // no es necesario utilizar column si no quiero configurar algo especifico
    private Long matricula;
    private transient List<Filtro> filtros; // dato transitorio, no persiste.
    public Usuario() {
    }
    public Usuario(String nombre, String apellido, long dni, long telefono, String username, String password, String email, String rolEnString, Long matricula) {
        super(nombre, apellido, dni, telefono);
        this.username = username;
        this.password = password;
        this.email = email;
        this.isApprobed = false;
        this.matricula = matricula;
        this.filtros = filtros;
        switch (rolEnString) {
            case "ADMIN":
                this.rol = Roles.ADMIN;
                break;
            case "SALUD":
                this.rol = Roles.SALUD;
                break;
            case "VISITANTE":
                this.rol = Roles.VISITANTE;
                break;
            default:
                throw new IllegalArgumentException("Rol no válido: " + rolEnString);
        }
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isApprobed() {
        return isApprobed;
    }

    public void setApprobed(Boolean Approbed) {
        isApprobed = Approbed;
    }
    public Roles getRol() {
        return rol;
    }
    public void setRol(Roles rol) {
        this.rol = rol;
    }
    public Long getMatricula() {
        return matricula;
    }
    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
    public List<Filtro> getFiltros() {
        return filtros;
    }
    public void setFiltros(List<Filtro> filtros) {
        this.filtros = filtros;
    }

    public Boolean getApprobed() {
        return isApprobed;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
