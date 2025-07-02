package Utils.Enums;

public enum TipoVivienda {
    LADRILLO("ladrillo"),
    MADERA("madera"),
    CHAPA("chapa"),
    MIXTO("mixto"),
    OTRO("otros"),
    NO_SABE("no sabe o no contesta");

    private final String descripcion;

    TipoVivienda(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoVivienda fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        for (TipoVivienda material : TipoVivienda.values()) {
            if (material.descripcion.equalsIgnoreCase(value.trim())) {
                return material;
            }
        }
        throw new IllegalArgumentException("Valor no reconocido: " + value);
    }
}
