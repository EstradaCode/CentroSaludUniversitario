package Utils.Enums;

public enum MetodoAnticonceptivo {
    PASTILLAS("Pastillas"),
    INYECCION("Inyección"),
    CHIP_IMPLANTE("Chip/implante subdérmico"),
    DIU("DIU"),
    SIU("SIU"),
    PRESERVATIVO("Preservativo"),
    LIGADURA("Ligadura"),
    VASECTOMIA("Vasectomía"),
    OTRO("Otro"),
    NO_SABE("No sabe o no contesta");

    private final String descripcion;

    MetodoAnticonceptivo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static MetodoAnticonceptivo fromString(String value) {
        for (MetodoAnticonceptivo metodo : MetodoAnticonceptivo.values()) {
            if (metodo.descripcion.equalsIgnoreCase(value.trim())) {
                return metodo;
            }
        }
        throw new IllegalArgumentException("Valor no reconocido: " + value);
    }
}
