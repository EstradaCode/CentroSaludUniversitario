package Utils.Converters;

import Utils.Enums.CoberturaSalud;
import com.opencsv.bean.AbstractBeanField;

import java.util.HashSet;
import java.util.Set;

public class CoberturaSaludConverter extends AbstractBeanField<Set<CoberturaSalud>, String> {
    @Override
    protected Set<CoberturaSalud> convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return Set.of(CoberturaSalud.NO_SABE);
        }

        String[] parts = value.split(",");
        Set<CoberturaSalud> coberturaSet = new HashSet<>();

        for (String part : parts) {
            String trimmedPart = part.trim();
            try {
                CoberturaSalud cobertura = CoberturaSalud.fromString(trimmedPart);
                coberturaSet.add(cobertura);
            } catch (IllegalArgumentException e) {
                // Si el valor no es reconocido, lo ignoramos
            }
        }

        return coberturaSet.isEmpty() ? Set.of(CoberturaSalud.NO_SABE) : coberturaSet;

    }
}
