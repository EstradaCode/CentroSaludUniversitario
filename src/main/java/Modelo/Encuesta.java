package Modelo;

import Utils.Enums.NivelIngresos;
import Utils.Enums.TipoCalefaccion;
import Utils.Enums.TipoConexionElectrica;
import Utils.Enums.TipoVivienda;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;


@Entity
public class Encuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private GeoPoint coordenadas;

    private Date fechaCreacion;

    @Enumerated(EnumType.STRING)
    private TipoVivienda tipoVivienda;

    private Boolean accesoAgua;

    private Boolean aguaPotable;

    @Enumerated(EnumType.STRING)
    private TipoConexionElectrica conexionElectrica;

    @ElementCollection(targetClass = TipoCalefaccion.class)
    @Enumerated(EnumType.STRING)
    private List<TipoCalefaccion> calefaccion;

    private Integer cantidadHabitaciones;

    private Boolean asistenciaAlimentaria;

    @Enumerated(EnumType.STRING)
    private NivelIngresos ingresos;

    private Boolean accesoSalud;

    @OneToMany(mappedBy = "encuesta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EncuestaPersona> personas = new ArrayList<>();

    @ManyToOne
    private Jornada jornada;

    private String uuidApi;

    public Encuesta() {}

    public Encuesta(GeoPoint coordenadas, Date fechaCreacion, TipoVivienda tipoVivienda,
                    Boolean accesoAgua, Boolean aguaPotable, TipoConexionElectrica conexionElectrica,
                    List<TipoCalefaccion> calefaccion, Integer cantidadHabitaciones,
                    Boolean asistenciaAlimentaria, NivelIngresos ingresos, Boolean accesoSalud,
                    List<EncuestaPersona> personas, Jornada jornada, String uuidApi) {
        this.coordenadas = coordenadas;
        this.fechaCreacion = fechaCreacion;
        this.tipoVivienda = tipoVivienda;
        this.accesoAgua = accesoAgua;
        this.aguaPotable = aguaPotable;
        this.conexionElectrica = conexionElectrica;
        this.calefaccion = calefaccion != null ? calefaccion : new ArrayList<>();
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.asistenciaAlimentaria = asistenciaAlimentaria;
        this.ingresos = ingresos;
        this.accesoSalud = accesoSalud;
        this.personas = personas != null ? personas : new ArrayList<>();
        this.jornada = jornada;
        this.uuidApi = uuidApi;
    }
    public Long getId() {
        return id;
    }

    public GeoPoint getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(GeoPoint coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public TipoVivienda getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(TipoVivienda tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public Boolean getAccesoAgua() {
        return accesoAgua;
    }

    public void setAccesoAgua(Boolean accesoAgua) {
        this.accesoAgua = accesoAgua;
    }

    public Boolean getAguaPotable() {
        return aguaPotable;
    }

    public void setAguaPotable(Boolean aguaPotable) {
        this.aguaPotable = aguaPotable;
    }

    public TipoConexionElectrica getConexionElectrica() {
        return conexionElectrica;
    }

    public void setConexionElectrica(TipoConexionElectrica conexionElectrica) {
        this.conexionElectrica = conexionElectrica;
    }

    public List<TipoCalefaccion> getCalefaccion() {
        return calefaccion;
    }

    public void setCalefaccion(List<TipoCalefaccion> calefaccion) {
        this.calefaccion = calefaccion;
    }

    public Integer getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setCantidadHabitaciones(Integer cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }

    public Boolean getAsistenciaAlimentaria() {
        return asistenciaAlimentaria;
    }

    public void setAsistenciaAlimentaria(Boolean asistenciaAlimentaria) {
        this.asistenciaAlimentaria = asistenciaAlimentaria;
    }

    public NivelIngresos getIngresos() {
        return ingresos;
    }

    public void setIngresos(NivelIngresos ingresos) {
        this.ingresos = ingresos;
    }

    public Boolean getAccesoSalud() {
        return accesoSalud;
    }

    public void setAccesoSalud(Boolean accesoSalud) {
        this.accesoSalud = accesoSalud;
    }

    public List<EncuestaPersona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<EncuestaPersona> personas) {
        this.personas = personas;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public String getUuidApi() {
        return uuidApi;
    }

    public void setUuidApi(String uuidApi) {
        this.uuidApi = uuidApi;
    }

}

