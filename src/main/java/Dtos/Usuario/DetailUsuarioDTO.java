package Dtos.Usuario;

import io.swagger.v3.oas.annotations.media.Schema;

public class DetailUsuarioDTO {
        private Long id;
        private String nombre;
        private String apellido;
        private Long dni;
        private Long telefono;
        private String username;
        private String email;
        private Boolean enabled;
        private String rol;
        private Long matricula;

        @Schema(description = "Nueva contraseña (solo para actualización)", example = "nuevaClave123", writeOnly = true)
        private String password;

    public DetailUsuarioDTO(Long id, String nombre, String apellido, Long dni, Long telefono, String username, String email, Boolean enabled, String rol, Long matricula, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.rol = rol;
        this.matricula = matricula;
        this.password = password;
    }

    public DetailUsuarioDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}