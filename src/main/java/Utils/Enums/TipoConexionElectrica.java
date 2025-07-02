package Utils.Enums;

public enum TipoConexionElectrica {
    CON_MEDIDOR("con medidor"),
    ENGANCHADO("enganchado"),
    NO_TIENE("no tiene"),
    NO_SABE("no sabe o no contesta");

    private final String descripcion;

    TipoConexionElectrica(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoConexionElectrica fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return NO_SABE;
        }

        for (TipoConexionElectrica conexion : TipoConexionElectrica.values()) {
            if (conexion.descripcion.equalsIgnoreCase(value.trim())) {
                return conexion;
            }
        }
        throw new IllegalArgumentException("Valor no reconocido: " + value);
    }
}

