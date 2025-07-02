package Utils.Enums;

public enum CantidadHabitaciones {
    UNA("1"),
    DOS("2"),
    TRES("3"),
    MAS_DE_TRES("mas de 3"),
    NS_NC("No sabe o no contesta");

    private final String descripcion;

    CantidadHabitaciones(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static CantidadHabitaciones fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return NS_NC;
        }
        value = value.trim().toLowerCase();
        switch (value) {
            case "1":
            case "1 (una)":
                return UNA;
            case "2":
            case "2 (dos)":
                return DOS;
            case "3":
            case "3 (tres)":
                return TRES;
            case "mas de 3":
                return MAS_DE_TRES;
            case "no sabe o no contesta":
                return NS_NC;
            default:
                throw new IllegalArgumentException("Valor desconocido: " + value);
        }
    }
}