package com.modelo;

import java.util.List;

public class Administrador extends Usuario{
    private List<String> accesosEspeciales;

    public Administrador(String nombre, String apellido, long dni, long telefono, String username, String password, String email, List<String> accesosEspeciales) {
        super(nombre, apellido, dni, telefono, username, password, email);
        this.accesosEspeciales = accesosEspeciales;
    }
    public Administrador() {

    }
    public List<String> getAccesosEspeciales() {
        return accesosEspeciales;
    }
    public void setAccesosEspeciales(List<String> accesosEspeciales) {
        this.accesosEspeciales = accesosEspeciales;
    }


}
