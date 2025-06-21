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

}


