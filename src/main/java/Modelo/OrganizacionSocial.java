package Modelo;

import jakarta.persistence.Entity;

@Entity
public class OrganizacionSocial {
    private Long id;
    private String nombre;
    private String domicilio;
    private Barrio barrio;
    private String actividadPrincipal;

    public OrganizacionSocial() {
    }
    public OrganizacionSocial(String nombre, String domicilio, Barrio barrio, String actividadPrincipal) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.barrio = barrio;
        this.actividadPrincipal = actividadPrincipal;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public Barrio getBarrio() {
        return barrio;
    }
    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }
    public String getActividadPrincipal() {
        return actividadPrincipal;
    }
    public void setActividadPrincipal(String actividadPrincipal) {
        this.actividadPrincipal = actividadPrincipal;
    }
}
