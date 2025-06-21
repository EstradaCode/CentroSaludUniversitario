package Utils.Enums;

public enum TipoVivienda {
    LADRILLO("Ladrillo"),
    MADERA("Madera"),
    CHAPA("Chapa"),
    MIXTO("Mixto"),
    OTRO("Otros"),
    NO_SABE("No sabe o no contesta");

    private final String descripcion;

    TipoVivienda(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoVivienda fromString(String value) {
        for (TipoVivienda material : TipoVivienda.values()) {
            if (material.descripcion.equalsIgnoreCase(value.trim())) {
                return material;
            }
        }
        throw new IllegalArgumentException("Valor no reconocido: " + value);
    }
}
