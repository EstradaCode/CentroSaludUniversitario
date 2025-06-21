package Utils.Enums;

public enum Enfermedad {
    CARDIOVASCULARES("Cardiovasculares"),
    RESPIRATORIOS("Respiratorios"),
    PIEL("En la piel"),
    VISTA("En la vista"),
    GASTROINTESTINALES("Gastrointestinales"),
    GINECOLOGICOS("Ginecológicos"),
    ALERGIAS("Alergias"),
    HUESOS_ARTICULACIONES("En los huesos/articulaciones"),
    SALUD_MENTAL("Salud mental"),
    DIABETES("Diabetes"),
    HIPERTENSION("Hipertensión"),
    TIROIDES("Problemas de tiroides"),
    ODONTOLOGICOS("Odontológicos"),
    CANCER("Cáncer"),
    ITS("Infección de transmisión sexual"),
    NINGUNA("Ninguna"),
    NO_SABE("No sabe o no contesta");

    private final String descripcion;

    Enfermedad(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static Enfermedad fromString(String value) {
        for (Enfermedad enfermedad : Enfermedad.values()) {
            if (enfermedad.descripcion.equalsIgnoreCase(value.trim())) {
                return enfermedad;
            }
        }
        throw new IllegalArgumentException("Valor no reconocido: " + value);
    }

    // Agrupación opcional por tipo de enfermedad para análisis posterior
    public String getGrupo() {
        return switch (this) {
            case CARDIOVASCULARES, DIABETES, HIPERTENSION, TIROIDES -> "Crónicas";
            case RESPIRATORIOS, GASTROINTESTINALES, ODONTOLOGICOS, ITS -> "Agudas/Comunes";
            case SALUD_MENTAL -> "Salud Mental";
            case GINECOLOGICOS -> "Ginecológicas";
            case PIEL, VISTA, ALERGIAS, HUESOS_ARTICULACIONES -> "Otras";
            case CANCER -> "Graves";
            case NINGUNA, NO_SABE -> "Sin enfermedad o no informado";
        };
    }
}

