package Utils.Enums;

public enum NivelIngresos {
    NO_ALCANZA("No les alcanza para vivir"),
    ALCANZA_NECESIDADES_BASICAS("Les alcanza para cubrir las necesidades básicas"),
    ALCANZA_AHORRAR("Les alcanza para poder ahorrar"),
    NO_SABE("No sabe o no contesta");

    private final String descripcion;

    NivelIngresos(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static NivelIngresos fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return NO_SABE;
        }

        for (NivelIngresos percepcion : NivelIngresos.values()) {
            if (percepcion.descripcion.equalsIgnoreCase(value.trim())) {
                return percepcion;
            }
        }
        throw new IllegalArgumentException("Valor no reconocido: " + value);
    }
}
