package Modelo;

import Utils.Enums.*;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class EncuestaPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Encuesta encuesta;

    private Integer edad;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    private NivelEducativo nivelEducativo;

    @Enumerated(EnumType.STRING)
    private TipoEmpleo tipoEmpleo;

    @Enumerated(EnumType.STRING)
    private AtencionSalud coberturaSalud;

    @ElementCollection(targetClass = Enfermedad.class)
    @Enumerated(EnumType.STRING)
    private Set<Enfermedad> enfermedades;

    @ElementCollection(targetClass = MetodoAnticonceptivo.class)
    @Enumerated(EnumType.STRING)
    private Set<MetodoAnticonceptivo> anticonceptivos;

    @Enumerated(EnumType.STRING)
    private AccesoMedicacion accesoMedicacion;

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

    public TipoEmpleo getTipoEmpleo() {
        return tipoEmpleo;
    }

    public void setTipoEmpleo(TipoEmpleo tipoEmpleo) {
        this.tipoEmpleo = tipoEmpleo;
    }

    public AtencionSalud getCoberturaSalud() {
        return coberturaSalud;
    }

    public void setCoberturaSalud(AtencionSalud coberturaSalud) {
        this.coberturaSalud = coberturaSalud;
    }

    public Set<Enfermedad> getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(Set<Enfermedad> enfermedades) {
        this.enfermedades = enfermedades;
    }

    public Set<MetodoAnticonceptivo> getAnticonceptivos() {
        return anticonceptivos;
    }

    public void setAnticonceptivos(Set<MetodoAnticonceptivo> anticonceptivos) {
        this.anticonceptivos = anticonceptivos;
    }

    public AccesoMedicacion getAccesoMedicacion() {
        return accesoMedicacion;
    }

    public void setAccesoMedicacion(AccesoMedicacion accesoMedicacion) {
        this.accesoMedicacion = accesoMedicacion;
    }
}


