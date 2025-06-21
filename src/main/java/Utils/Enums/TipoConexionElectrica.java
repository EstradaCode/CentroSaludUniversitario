package Utils.Enums;

public enum TipoConexionElectrica {
    CON_MEDIDOR("Con medidor"),
    ENGANCHADO("Enganchado"),
    NO_TIENE("No tiene"),
    NO_SABE("No sabe o no contesta");

    private final String descripcion;

    TipoConexionElectrica(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoConexionElectrica fromString(String value) {
        for (TipoConexionElectrica conexion : TipoConexionElectrica.values()) {
            if (conexion.descripcion.equalsIgnoreCase(value.trim())) {
                return conexion;
            }
        }
        throw new IllegalArgumentException("Valor no reconocido: " + value);
    }
}

