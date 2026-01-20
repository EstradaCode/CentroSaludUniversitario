package Modelo;

import Utils.Converters.*;
import Utils.Enums.*;
import com.opencsv.bean.*;
import jakarta.persistence.*;

import java.util.*;


@Entity
public class Encuesta {
    @CsvBindByPosition(position = 0)
    private String uuidApi;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByPosition(position = 4)
    private transient double latitud;

    @CsvBindByPosition(position = 5)
    private transient double longitud;

    @Embedded
    private GeoPoint coordenadas;
    @CsvCustomBindByPosition(position = 1, converter = IsoDateConverter.class)
    private Date fechaCreacion;

    @Enumerated(EnumType.STRING)
    @CsvCustomBindByPosition(position = 14, converter = TipoViviendaConverter.class)
    private TipoVivienda tipoVivienda;
    @CsvCustomBindByPosition(position = 15,converter = YesOrNoConverter.class)
    private Boolean accesoAgua;
    @CsvCustomBindByPosition(position = 17,converter = YesOrNoConverter.class)
    private Boolean aguaPotable;

    @ElementCollection(targetClass = TipoCalefaccion.class)
    @Enumerated(EnumType.STRING)
    @CsvCustomBindByPosition(position = 21, converter = CalefaccionConverter.class)
    private Set<TipoCalefaccion> calefaccion;

    @Enumerated(EnumType.STRING)
    @CsvCustomBindByPosition(position = 22, converter = TipoConexionElectricaConverter.class)
    private TipoConexionElectrica conexionElectrica;


    @CsvCustomBindByPosition(position = 24, converter = CantidadHabitacionesConverter.class)
    private CantidadHabitaciones cantidadHabitaciones;

    @Enumerated(EnumType.STRING)
    @CsvCustomBindByPosition(position = 26, converter = NivelIngresosConverter.class)
    private NivelIngresos ingresos;

    @CsvCustomBindByPosition(position = 27, converter = YesOrNoConverter.class)
    private Boolean asistenciaAlimentaria;


    @ElementCollection(targetClass = AtencionSalud.class)
    @Enumerated(EnumType.STRING)
    @CsvCustomBindByPosition(position = 29, converter = AtencionSaludConverter.class)
    private Set<AtencionSalud> atencionSalud;

    @ElementCollection(targetClass = AccesoMedicacion.class)
    @Enumerated(EnumType.STRING)
    @CsvCustomBindByPosition(position = 60, converter = AccesoMedicacionConverter.class)
    private Set<AccesoMedicacion> accesoMedicacion;

    @ElementCollection(targetClass = MetodoAnticonceptivo.class)
    @Enumerated(EnumType.STRING)
    @CsvCustomBindByPosition(position = 65, converter = MetodoAnticonceptivoConverter.class)
    private Set<MetodoAnticonceptivo> anticonceptivos;

    @OneToMany(mappedBy = "encuesta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EncuestaPersona> personas = new ArrayList<>();

    @ManyToOne
    private Jornada jornada;


    public Encuesta() {}

    public Encuesta(Long id, GeoPoint coordenadas, Date fechaCreacion, TipoVivienda tipoVivienda, Boolean accesoAgua, Boolean aguaPotable, TipoConexionElectrica conexionElectrica, Set<TipoCalefaccion> calefaccion, CantidadHabitaciones cantidadHabitaciones, Boolean asistenciaAlimentaria, NivelIngresos ingresos, Set<AtencionSalud> atencionSalud, Set<MetodoAnticonceptivo> anticonceptivos, Set<AccesoMedicacion> accesoMedicacion, List<EncuestaPersona> personas, Jornada jornada, String uuidApi) {
        this.id = id;
        this.coordenadas = coordenadas;
        this.fechaCreacion = fechaCreacion;
        this.tipoVivienda = tipoVivienda;
        this.accesoAgua = accesoAgua;
        this.aguaPotable = aguaPotable;
        this.conexionElectrica = conexionElectrica;
        this.calefaccion = calefaccion;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.asistenciaAlimentaria = asistenciaAlimentaria;
        this.ingresos = ingresos;
        this.atencionSalud = atencionSalud;
        this.anticonceptivos = anticonceptivos;
        this.accesoMedicacion = accesoMedicacion;
        this.personas = personas;
        this.jornada = jornada;
        this.uuidApi = uuidApi;
    }

    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
        updateCoordenadas();
    }

    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
        updateCoordenadas();
    }
    private void updateCoordenadas() {
        if (latitud != 0.0 && longitud != 0.0) {
            try {

                this.coordenadas = new GeoPoint(latitud, longitud);
            } catch (NumberFormatException e) {
                this.coordenadas = null;
            }
        }
    }
    public void setCoordenadas(GeoPoint coordenadas) {
        this.coordenadas = coordenadas;
    }
    public Long getId() {
        return id;
    }

    public GeoPoint getCoordenadas() {
        return coordenadas;
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

    public Set<TipoCalefaccion> getCalefaccion() {
        return calefaccion;
    }

    public void setCalefaccion(Set<TipoCalefaccion> calefaccion) {
        this.calefaccion = calefaccion;
    }

    public CantidadHabitaciones getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setCantidadHabitaciones(CantidadHabitaciones cantidadHabitaciones) {
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

    public Set<AtencionSalud> getAtencionSalud() {
        return atencionSalud;
    }

    public void setAtencionSalud(Set<AtencionSalud> atencionSalud) {
        this.atencionSalud = atencionSalud;
    }

    public List<EncuestaPersona> getPersonas() {
        return personas;
    }

    public void addPersona(EncuestaPersona persona) {
        if (personas == null) {
            personas = new ArrayList<>();
        }
        personas.add(persona);
        persona.setEncuesta(this);
    }

    public void setPersonas(List<EncuestaPersona> personas) {
        this.personas = new ArrayList<>();
        if (personas != null) {
            for (EncuestaPersona persona : personas) {
                addPersona(persona);
            }
        }
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

    public void setId(Long id) {
        this.id = id;
    }

    public Set<MetodoAnticonceptivo> getAnticonceptivos() {
        return anticonceptivos;
    }

    public void setAnticonceptivos(Set<MetodoAnticonceptivo> anticonceptivos) {
        this.anticonceptivos = anticonceptivos;
    }

    public Set<AccesoMedicacion> getAccesoMedicacion() {
        return accesoMedicacion;
    }

    public void setAccesoMedicacion(Set<AccesoMedicacion> accesoMedicacion) {
        this.accesoMedicacion = accesoMedicacion;
    }
    @Override
    public String toString() {
        return "Encuesta{" +
                "id=" + id +
                ", coordenadas=" + coordenadas +
                ", fechaCreacion=" + fechaCreacion +
                ", tipoVivienda=" + tipoVivienda +
                ", accesoAgua=" + accesoAgua +
                ", aguaPotable=" + aguaPotable +
                ", conexionElectrica=" + conexionElectrica +
                ", calefaccion=" + calefaccion +
                ", cantidadHabitaciones=" + cantidadHabitaciones +
                ", asistenciaAlimentaria=" + asistenciaAlimentaria +
                ", ingresos=" + ingresos +
                ", atencionSalud=" + atencionSalud +
                ", anticonceptivos=" + anticonceptivos +
                ", accesoMedicacion=" + accesoMedicacion +
                ", uuidApi='" + uuidApi + '\'' +
                ", cantidadPersonas=" + (personas != null ? personas.size() : 0) +
                '}';
    }

}

