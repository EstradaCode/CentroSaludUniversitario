package com.modelo;

public class Visitante extends Usuario{
    private String[] permisos;
    public Visitante(){
    }
    public Visitante(String nombre, String apellido, long dni, long telefono, String username, String password, String email, String[] permisos) {
        super(nombre, apellido, dni, telefono, username, password, email);
        this.permisos = permisos;
    }
    public String[] getPermisos() {
        return permisos;
    }
    public void setPermisos(String[] permisos) {
        this.permisos = permisos;
    }

}
