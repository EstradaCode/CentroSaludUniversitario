package Utils.Enums;

public enum CoberturaSalud {
    PAMI("PAMI/Incluir Salud"),
    PREPAGA("Prepaga"),
    OS("Obra Social/Mutual"),
    PUBLICO("Sistema Publico de Salud"),
    NO_SABE("No sabe o no contesta");
    private final String descripcion;
    CoberturaSalud(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public static CoberturaSalud fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return NO_SABE;
        }
        for (CoberturaSalud cobertura : CoberturaSalud.values()) {
            if (cobertura.descripcion.equalsIgnoreCase(value.trim())) {
                return cobertura;
            }
        }
        throw new IllegalArgumentException("Valor no reconocido: " + value);
    }
    public String agrupar() {
        return switch (this) {
            case PAMI,PREPAGA, OS -> "Con cobertura de salud";
            case PUBLICO -> "Sin cobertura de salud";
            case NO_SABE ->  "NS/NC";
        };
    }

}

