package Utils.Converters;

import Utils.Enums.TipoVivienda;
import com.opencsv.bean.AbstractBeanField;

public class TipoViviendaConverter extends AbstractBeanField<TipoVivienda, String> {
    @Override
    protected TipoVivienda convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null; // Manejo de valores nulos o vacíos
        }
        try {
            return TipoVivienda.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor no reconocido para TipoVivienda: " + value, e);
        }
    }
}
