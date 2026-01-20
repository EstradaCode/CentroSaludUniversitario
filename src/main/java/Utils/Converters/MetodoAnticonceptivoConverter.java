package Utils.Converters;

import Utils.Enums.MetodoAnticonceptivo;
import com.opencsv.bean.AbstractBeanField;
import org.apache.commons.beanutils.converters.AbstractConverter;
import java.util.HashSet;

import java.util.Set;

public class MetodoAnticonceptivoConverter extends AbstractBeanField<Set<MetodoAnticonceptivo>, String> {
    @Override
    protected Set<MetodoAnticonceptivo> convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return Set.of(MetodoAnticonceptivo.NO_SABE);
        }

        String[] parts = value.split(",");
        Set<MetodoAnticonceptivo> metodoSet = new HashSet<MetodoAnticonceptivo>();

        for (String part : parts) {
            String trimmedPart = part.trim();
            try {
                MetodoAnticonceptivo metodo = MetodoAnticonceptivo.fromString(trimmedPart);
                metodoSet.add(metodo);
            } catch (IllegalArgumentException e) {
                // Si el valor no es reconocido, lo ignoramos
            }
        }

        return metodoSet.isEmpty() ? Set.of(MetodoAnticonceptivo.NO_SABE) : metodoSet;
    }
}
