package com.modelo;

import java.util.List;

enum Roles {
    ADMIN,
    SALUD,
    VISITANTE
}
public class Usuario extends Persona{

    private String username;
    private String password;
    private String email;
    private Boolean isApprobed;
    private Roles rol;
    private Long matricula;
    private List<Filtro> filtros;
    public Usuario() {
    }
    public Usuario(String nombre, String apellido, long dni, long telefono, String username, String password, String email, String rolEnString, Long matricula, List<Filtro> filtros) {
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

}
