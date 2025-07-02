package Utils.Enums;

public enum TipoCalefaccion {
    GAS_RED("Con gas de red"),
    GAS_ENVASADO("Gas envasado (garrafa/tubo)"),
    ELECTRICO("Eléctrico"),
    LENIA_CARBON("Leña - carbón"),
    OTRO("Otro combustible"),
    NO_SABE("No sabe o no contesta");

    private final String descripcion;

    TipoCalefaccion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoCalefaccion fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return NO_SABE;
        }
        for (TipoCalefaccion tipo : TipoCalefaccion.values()) {
            if (tipo.descripcion.equalsIgnoreCase(value.trim())) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor no reconocido: " + value);
    }
}
