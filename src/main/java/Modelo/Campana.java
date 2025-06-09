package com.modelo;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Campana {
    private String nombre;
    private File archivoEncuesta;
    private List<Barrio> zona;
    private String fechaInicio;
    private String fechaFin;
    private List<Encuestador>  encuestadores;
    private OrganizacionSocial organizacionSocial;

    public Campana() {
    }
    public Campana(String nombre, File archivoEncuesta, List<Barrio> zona, String fechaInicio, String fechaFin, List<Encuestador> encuestadores, OrganizacionSocial organizacionSocial) {
        this.nombre = nombre;
        this.archivoEncuesta = archivoEncuesta;
        this.zona = zona;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.encuestadores = encuestadores;
        this.organizacionSocial = organizacionSocial;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public File getArchivoEncuesta() {
        return archivoEncuesta;
    }
    public void setArchivoEncuesta(File archivoEncuesta) {
        this.archivoEncuesta = archivoEncuesta;
    }
    public List<Barrio> getZona() {
        return zona;
    }
    public void setZona(List<Barrio> zona) {
        this.zona = zona;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    public List<Encuestador> getEncuestadores() {
        return encuestadores;
    }
    public void setEncuestadores(List<Encuestador> encuestadores) {
        this.encuestadores = encuestadores;
    }
    public OrganizacionSocial getOrganizacionSocial() {
        return organizacionSocial;
    }
    public void setOrganizacionSocial(OrganizacionSocial organizacionSocial) {
        this.organizacionSocial = organizacionSocial;
    }

}
