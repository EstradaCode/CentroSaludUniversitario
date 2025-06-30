package Utils.Converters;

import Utils.Enums.Enfermedad;
import com.opencsv.bean.AbstractBeanField;

import java.util.Set;

public class EnfermedadesConverter extends AbstractBeanField<Set<Enfermedad>, String> {

    @Override
    protected Set<Enfermedad> convert(String value) {
        String[] enfermedadesArray = value.split(",");
        Set<Enfermedad> enfermedadesSet = Set.of();
        for (String enfermedad : enfermedadesArray) {
            Enfermedad enfermedadEnum = Enfermedad.fromString(enfermedad.trim());
            if (enfermedadEnum != null) {
                enfermedadesSet.add(enfermedadEnum);
            }
        }
        return enfermedadesSet;
    }
}
