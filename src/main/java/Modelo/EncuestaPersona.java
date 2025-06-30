package Modelo;

import Utils.Converters.CoberturaSaludConverter;
import Utils.Converters.EnfermedadesConverter;
import Utils.Converters.GeneroConverter;
import Utils.Converters.TipoEmpleoConverter;
import Utils.Enums.*;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class EncuestaPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Encuesta encuesta;
    @CsvBindByName(column = "8_3_Edad")
    private Integer edad;

    @Enumerated(EnumType.STRING)
    @CsvCustomBindByName(converter = GeneroConverter.class, column = "8_4_Genero")
    private Genero genero;

    @Enumerated(EnumType.STRING)
    @CsvBindByName(column = "13_7_Para_mayores_de")
    private NivelEducativo nivelEducativo;

    @ElementCollection(targetClass = TipoEmpleo.class)
    @Enumerated(EnumType.STRING)
    @CsvCustomBindByName(column = "16_10_En_cules_de_la", converter = TipoEmpleoConverter.class)
    private Set<TipoEmpleo> empleos;

    @Enumerated(EnumType.STRING)
    @CsvCustomBindByName(column = "22_15_Tiene_alguna_d", converter = CoberturaSaludConverter.class)
    private CoberturaSalud coberturaSalud;

    @ElementCollection(targetClass = Enfermedad.class)
    @Enumerated(EnumType.STRING)
    @CsvCustomBindByName(column = "27_20_Tiene_o_tuvo_e", converter = EnfermedadesConverter.class)
    private Set<Enfermedad> enfermedades;

    @CsvBindByName(column = "ec5_branch_owner_uuid")
    private transient String ownerUuid;

    public EncuestaPersona() {
    }
    public EncuestaPersona(Encuesta encuesta, Integer edad, Genero genero, NivelEducativo nivelEducativo, Set<TipoEmpleo> empleos, CoberturaSalud coberturaSalud, Set<Enfermedad> enfermedades) {
        this.encuesta = encuesta;
        this.edad = edad;
        this.genero = genero;
        this.nivelEducativo = nivelEducativo;
        this.empleos = empleos;
        this.coberturaSalud = coberturaSalud;
        this.enfermedades = enfermedades;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public NivelEducativo getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(NivelEducativo nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public Set<TipoEmpleo> getEmpleos() {
        return empleos;
    }

    public void setEmpleos(Set<TipoEmpleo> empleos) {
        this.empleos = empleos;
    }

    public CoberturaSalud getCoberturaSalud() {
        return coberturaSalud;
    }

    public void setCoberturaSalud(CoberturaSalud coberturaSalud) {
        this.coberturaSalud = coberturaSalud;
    }

    public Set<Enfermedad> getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(Set<Enfermedad> enfermedades) {
        this.enfermedades = enfermedades;
    }

    public String getOwnerUuid() {
        return ownerUuid;
    }

    public void setOwnerUuid(String ownerUuid) {
        this.ownerUuid = ownerUuid;
    }
}


