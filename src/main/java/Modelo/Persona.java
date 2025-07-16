package Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Schema(description = "Entidad base que representa una persona. Se extiende por otras entidades como Usuario o Encuestador.")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la persona", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre de la persona", example = "Ludmila", required = true)
    private String nombre;

    @Schema(description = "Apellido de la persona", example = "Pérez", required = true)
    private String apellido;

    @Schema(description = "DNI de la persona", example = "45123456", required = true)
    private Long dni;

    @Schema(description = "Teléfono de contacto", example = "1122334455", required = true)
    private Long telefono;

    public Persona() {
    }

    public Persona(String nombre, String apellido, Long dni, Long telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getDni() {
        return dni;
    }
    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Long getTelefono() {
        return telefono;
    }
    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
}
