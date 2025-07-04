package Utils.Converters;

import Utils.Enums.TipoEmpleo;
import com.opencsv.bean.AbstractBeanField;

import java.util.HashSet;
import java.util.Set;

public class TipoEmpleoConverter extends AbstractBeanField<Set<TipoEmpleo>, String> {
    @Override
    protected Set<TipoEmpleo> convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return Set.of(); // Retorna un conjunto vacío si el valor es nulo o vacío
        }

        String[] parts = value.split(",");
        Set<TipoEmpleo> tipos = new HashSet<TipoEmpleo>();

        for (String part : parts) {
            try {
                TipoEmpleo tipo = TipoEmpleo.fromString(part.trim());
                tipos.add(tipo);
            } catch (IllegalArgumentException e) {
                // Manejo de error: si el valor no es reconocido, se ignora
            }
        }

        return tipos;
    }
}
