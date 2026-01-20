package Utils.Converters;

import Utils.Enums.AtencionSalud;
import com.opencsv.bean.AbstractBeanField;

import java.util.HashSet;
import java.util.Set;

public class AtencionSaludConverter extends AbstractBeanField<Set<AtencionSalud>, String> {
    @Override
    protected Set<AtencionSalud> convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return Set.of(AtencionSalud.NO_SABE);
        }

        String[] parts = value.split(",");
        Set<AtencionSalud> atencionSet = new HashSet<AtencionSalud>();

        for (String part : parts) {
            String trimmedPart = part.trim();
            try {
                AtencionSalud atencion = AtencionSalud.fromString(trimmedPart);
                atencionSet.add(atencion);
            } catch (IllegalArgumentException e) {
                // Si el valor no es reconocido, lo ignoramos
            }
        }

        return atencionSet.isEmpty() ? Set.of(AtencionSalud.NO_SABE) : atencionSet;
    }
}
