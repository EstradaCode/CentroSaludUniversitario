package Modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Campania {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String rutaArchivoEncuesta; // la idea es que el archivo esté subido en un servicio aparte, para consumirse de forma externa (google cloud, amazon s3, etc)
    @ManyToMany
    private List<Barrio> zona;
    private String fechaInicio;
    private String fechaFin;
    @OneToMany
    private List<Encuestador>  encuestadores;
    @OneToMany
    private List<Jornada> jornadas;
    @ManyToOne
    private OrganizacionSocial organizacionSocial;

    public Campania() {
    }
    public Campania(String nombre, String archivoEncuesta, List<Barrio> zona, String fechaInicio, String fechaFin, List<Encuestador> encuestadores, OrganizacionSocial organizacionSocial) {
        this.nombre = nombre;
        this.rutaArchivoEncuesta = archivoEncuesta;
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
    public String getArchivoEncuesta() {
        return rutaArchivoEncuesta;
    }
    public void setArchivoEncuesta(String archivoEncuesta) {
        this.rutaArchivoEncuesta = archivoEncuesta;
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
