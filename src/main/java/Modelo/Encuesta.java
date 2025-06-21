package Modelo;

import Utils.Enums.NivelIngresos;
import Utils.Enums.TipoCalefaccion;
import Utils.Enums.TipoConexionElectrica;
import Utils.Enums.TipoVivienda;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<EncuestaPersona> personas;

    private String uuidApi;

}

