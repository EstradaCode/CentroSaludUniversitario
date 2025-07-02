package Utils.Enums;

public enum NivelEducativo {
    JARDIN_INICIAL("Jardin inicial (4 a 5 años)"),
    PRIMARIO_EN_CURSO("primario en curso"),
    PRIMARIO_COMPLETO("primario completo"),
    PRIMARIO_INCOMPLETO("primario incompleto"),
    SECUNDARIO_EN_CURSO("secundario en curso"),
    SECUNDARIO_COMPLETO("secundario completo"),
    SECUNDARIO_INCOMPLETO("secundario incompleto"),
    TERCIARIO_UNIVERSITARIO_EN_CURSO("terciario-universitario en curso"),
    TERCIARIO_UNIVERSITARIO_COMPLETO("terciario-universitario completo"),
    TERCIARIO_UNIVERSITARIO_INCOMPLETO("terciario-universitario incompleto"),
    OFICIO("oficio"),
    NO_CONSIGUE_VACANTE("no consigue vacante"),
    NUNCA_ASISTIO("nunca asistio a una institución educativa"),
    NO_CORRESPONDE("no corresponde (menores de 4 años)");

    private final String descripcion;

    NivelEducativo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static NivelEducativo fromDescripcion(String descripcion) {
        for (NivelEducativo nivel : values()) {
            if (nivel.getDescripcion().equalsIgnoreCase(descripcion.trim())) {
                return nivel;
            }
        }
        throw new IllegalArgumentException("Valor no reconocido: " + descripcion);
    }


// Agrupamiento útil para análisis
    public String getGrupo() {
        return switch (this) {
            case JARDIN_INICIAL -> "Inicial";
            case PRIMARIO_EN_CURSO, PRIMARIO_COMPLETO, PRIMARIO_INCOMPLETO -> "Primario";
            case SECUNDARIO_EN_CURSO, SECUNDARIO_COMPLETO, SECUNDARIO_INCOMPLETO -> "Secundario";
            case TERCIARIO_UNIVERSITARIO_EN_CURSO, TERCIARIO_UNIVERSITARIO_COMPLETO, TERCIARIO_UNIVERSITARIO_INCOMPLETO -> "Terciario";
            case OFICIO -> "Oficio";
            case NO_CONSIGUE_VACANTE, NUNCA_ASISTIO -> "Sin escolarización";
            case NO_CORRESPONDE -> "No corresponde";
        };
    }
}

