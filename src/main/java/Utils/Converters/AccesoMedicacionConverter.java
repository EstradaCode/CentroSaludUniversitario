package Utils.Converters;

import Utils.Enums.AccesoMedicacion;
import com.opencsv.bean.AbstractBeanField;

import java.util.Set;

public class AccesoMedicacionConverter extends AbstractBeanField<Set<AccesoMedicacion>, String> {
    @Override
    protected Set<AccesoMedicacion> convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return Set.of(); // Retorna un conjunto vacío si el valor es nulo o vacío
        }

        String[] parts = value.split(",");
        Set<AccesoMedicacion> accesos = Set.of();

        for (String part : parts) {
            try {
                AccesoMedicacion acceso = AccesoMedicacion.fromString(part.trim());
                accesos.add(acceso);
            } catch (IllegalArgumentException e) {
                // Manejo de error: si el valor no es reconocido, se ignora
            }
        }

        return accesos;
    }
}
