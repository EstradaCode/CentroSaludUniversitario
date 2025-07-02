package Utils.Enums;
import java.text.Normalizer;
public enum Genero {
    MUJER_CIS("mujer cis"),
    MUJER_TRANS_TRAVESTI("mujer trans-travesti"),
    VARON_CIS("varon cis"),
    VARON_TRANS("varon trans-masculinidad trans"),
    NO_BINARIE("no binarie"),
    OTRA_IDENTIDAD("otra identidad-ninguna de las anteriores"),
    NO_SABE_NO_CONTESTA("no sabe o no contesta");

    private final String descripcion;

    Genero(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static Genero fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return NO_SABE_NO_CONTESTA;
        }
        for (Genero genero : Genero.values()) {
            if (genero.descripcion.equalsIgnoreCase(sinAcentos(value.trim()))) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Valor no reconocido: " + value);
    }

    // Agrupamiento para análisis estadístico
    public String getGrupo() {
        return switch (this) {
            case MUJER_CIS, MUJER_TRANS_TRAVESTI -> "Mujeres";
            case VARON_CIS, VARON_TRANS -> "Varones";
            case NO_BINARIE, OTRA_IDENTIDAD -> "LGBT+";
            case NO_SABE_NO_CONTESTA -> "NS/NC";
        };
    }


    public static String sinAcentos(String texto) {
        if (texto == null) return null;
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return normalizado.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}

