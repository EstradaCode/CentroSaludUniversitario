package Dtos.Usuario;

public class PersonaDTO {
    @jakarta.validation.constraints.NotBlank private String nombre;
    @jakarta.validation.constraints.NotBlank private String apellido;
    @jakarta.validation.constraints.NotNull  private Long dni;
    private Long telefono; // si querés, también @NotNull

    public PersonaDTO(String nombre, String apellido, Long dni, Long telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
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