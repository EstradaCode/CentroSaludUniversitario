package Utils.Enums;

public enum AtencionSalud {
    CENTRO_SALUD_PUBLICO("Centro de salud o salita pública"),
    HOSPITAL_PUBLICO("Hospital público"),
    CLINICA_PRIVADA("Clínica privada"),
    CENTRO_OBRA_SOCIAL("Centro de salud de obra social"),
    POSTA_SALUD("Posta de salud"),
    OTRO("Otro"),
    NO_SABE("No sabe o no contesta");

    private final String descripcion;

    AtencionSalud(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static AtencionSalud fromString(String value) {
        for (AtencionSalud lugar : AtencionSalud.values()) {
            if (lugar.descripcion.equalsIgnoreCase(value.trim())) {
                return lugar;
            }
        }
        throw new IllegalArgumentException("Valor no reconocido: " + value);
    }
}
