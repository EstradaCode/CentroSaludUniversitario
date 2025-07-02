package Utils.Converters;

import Utils.Enums.TipoConexionElectrica;
import com.opencsv.bean.AbstractBeanField;

public class TipoConexionElectricaConverter extends AbstractBeanField<TipoConexionElectrica, String> {
    @Override
    protected TipoConexionElectrica convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null; // Manejo de valores nulos o vacíos
        }
        try {
            return TipoConexionElectrica.fromString(value.trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor no reconocido para TipoConexionElectrica: " + value, e);
        }
    }
}
