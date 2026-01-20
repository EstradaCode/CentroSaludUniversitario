package Utils.Converters;

import Utils.Enums.NivelIngresos;
import com.opencsv.bean.AbstractBeanField;

public class NivelIngresosConverter extends AbstractBeanField<NivelIngresos, String> {
    @Override
    protected NivelIngresos convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null; // Manejo de valores nulos o vacíos
        }
        try {
            return NivelIngresos.fromString(value.trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor no reconocido para NivelIngresos: " + value, e);
        }
    }
}
