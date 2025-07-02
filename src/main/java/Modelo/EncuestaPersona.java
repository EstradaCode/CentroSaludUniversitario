package Modelo;

import Utils.Converters.*;
import Utils.Enums.*;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvCustomBindByPosition;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class EncuestaPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encuesta_id")
    private Encuesta encuesta;

    @CsvBindByName(column = "8_3_Edad")
    private Integer edad;

    @Enumerated(EnumType.STRING)
    @CsvCustomBindByName(converter = GeneroConverter.class, column = "9_4_De_acuerdo_a_la_")
    private Genero genero;

    @Enumerated(EnumType.STRING)
    @CsvCustomBindByName(column = "13_7_Para_mayores_de", converter = NivelEducativoConverter.class)
    private NivelEducativo nivelEducativo;

    @ElementCollection(targetClass = TipoEmpleo.class)
    @Enumerated(EnumType.STRING)
    @CsvCustomBindByName(column = "16_10_En_cules_de_la", converter = TipoEmpleoConverter.class)
    private Set<TipoEmpleo> empleos;
    @ElementCollection(targetClass = CoberturaSalud.class)
    @Enumerated(EnumType.STRING)
    @CsvCustomBindByName(column = "22_15_Tiene_alguna_d", converter = CoberturaSaludConverter.class)
    private Set<CoberturaSalud> coberturaSalud;

    @ElementCollection(targetClass = Enfermedad.class)
    @Enumerated(EnumType.STRING)
    @CsvCustomBindByName(column = "27_20_Tiene_o_tuvo_e", converter = EnfermedadesConverter.class)
    private Set<Enfermedad> enfermedades;

    @CsvBindByPosition(position = 0)
    @CsvCustomBindByName(column = "ec5_branch_owner_uuid", converter = OwnerUuidConverter.class)
    private String ownerUuid;

    public EncuestaPersona() {
    }

    public EncuestaPersona(Encuesta encuesta, Integer edad, Genero genero, NivelEducativo nivelEducativo, Set<TipoEmpleo> empleos, Set<CoberturaSalud> coberturaSalud, Set<Enfermedad> enfermedades) {
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

    public Set<CoberturaSalud> getCoberturaSalud() {
        return coberturaSalud;
    }

    public void setCoberturaSalud(Set<CoberturaSalud> coberturaSalud) {
        this.coberturaSalud = coberturaSalud;
    }

    public Set<Enfermedad> getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(Set<Enfermedad> enfermedades) {
        this.enfermedades = enfermedades;
    }

    public String getOwnerUuid() {
        System.out.println("Getting ownerUuid: " + ownerUuid); // Debug line
        return ownerUuid;
    }

    public void setOwnerUuid(String ownerUuid) {
        System.out.println("Setting ownerUuid: " + ownerUuid); // Debug line
        this.ownerUuid = ownerUuid;
    }
    @Override
    public String toString() {
        return "EncuestaPersona{" +
                "id=" + id +
                ", edad=" + edad +
                ", genero=" + genero +
                ", nivelEducativo=" + nivelEducativo +
                ", empleos=" + empleos +
                ", coberturaSalud=" + coberturaSalud +
                ", enfermedades=" + enfermedades +
                ", ownerUuid='" + ownerUuid + '\'' +
                '}';
    }

}


