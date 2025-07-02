package Utils.Converters;

import Utils.Enums.NivelEducativo;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class NivelEducativoConverter extends AbstractBeanField<String, NivelEducativo> {
    @Override
    protected Object convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return NivelEducativo.fromDescripcion(value);

    }
}
