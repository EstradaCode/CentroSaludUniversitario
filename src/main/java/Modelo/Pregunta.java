package Modelo;

import jakarta.persistence.*;

enum TipoPregunta {
    NUMERO,
    TEXTO,
    OPCION_MULTIPLE,
}
enum Nivel{
    PERSONA,
    GRUPO
}

@Entity
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;
    @Enumerated(EnumType.STRING)
    private TipoPregunta tipo;
    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Pregunta() {}

    public Pregunta(Long id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public Pregunta(String texto) {
        this.texto = texto;
    }

}

