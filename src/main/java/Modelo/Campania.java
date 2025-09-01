package Modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class Campania {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String rutaArchivoEncuesta; // la idea es que el archivo esté subido en un servicio aparte, para consumirse de forma externa (google cloud, amazon s3, etc)
    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "barrio_id", nullable = false)
    private Barrio barrio;
    private boolean activa;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    @OneToMany
    private List<Encuestador>  encuestadores;
    @OneToMany(mappedBy = "campania", cascade = CascadeType.ALL)
    private List<Jornada> jornadas;
    @ManyToOne
    @JoinColumn(name = "organizacionSocial_id")
    private OrganizacionSocial organizacionSocial;
    @Version
    Long version;

    public Campania() {
    }
    public Campania(String nombre, String archivoEncuesta, Barrio barrio, LocalDate fechaInicio, LocalDate fechaFin, List<Encuestador> encuestadores, OrganizacionSocial organizacionSocial) {
        this.nombre = nombre;
        this.rutaArchivoEncuesta = archivoEncuesta;
        this.barrio = barrio;
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
    public Barrio getBarrio() {
        return barrio;
    }
    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDate fechaFin) {
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
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<Jornada> jornadas) {
        this.jornadas = jornadas;
    }

    public String getRutaArchivoEncuesta() {
        return rutaArchivoEncuesta;
    }

    public void setRutaArchivoEncuesta(String rutaArchivoEncuesta) {
        this.rutaArchivoEncuesta = rutaArchivoEncuesta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
