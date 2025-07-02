package Utils.Enums;

import com.opencsv.bean.AbstractBeanField;

public enum AccesoMedicacion {
    CENTRO_SALUD("Centro de salud o salita pública"),
    HOSPITAL_PUBLICO("Hospital público"),
    POSTA_SALUD("Posta/operativo de salud"),
    OBRA_SOCIAL("Obra social"),
    COMPRA("Lo compra"),
    NS_NC("No sabe o no contesta");

    private final String descripcion;

    AccesoMedicacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static AccesoMedicacion fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return NS_NC;
        }
        for (AccesoMedicacion acceso : AccesoMedicacion.values()) {
            if (acceso.descripcion.equalsIgnoreCase(value.trim())) {
                return acceso;
            }
        }
        throw new IllegalArgumentException("Valor no reconocido: " + value);
    }
}
