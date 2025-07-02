package Utils.Converters;

import Utils.Enums.TipoCalefaccion;
import com.opencsv.bean.AbstractBeanField;

import java.util.HashSet;
import java.util.Set;

public class CalefaccionConverter extends AbstractBeanField<Set<TipoCalefaccion>, String> {

    @Override
    protected Set<TipoCalefaccion> convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return Set.of(); // Retorna un conjunto vacío si el valor es nulo o vacío
        }

        String[] parts = value.split(",");
        Set<TipoCalefaccion> calefacciones = new HashSet<>();

        for (String part : parts) {
            try {
                TipoCalefaccion calefaccion = TipoCalefaccion.fromString(part.trim());
                calefacciones.add(calefaccion);
            } catch (IllegalArgumentException e) {
                // Manejo de error: si el valor no es reconocido, se ignora
            }
        }

        return calefacciones;
    }
}
