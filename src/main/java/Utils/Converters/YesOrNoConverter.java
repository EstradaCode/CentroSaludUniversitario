package Utils.Converters;

import com.opencsv.bean.AbstractBeanField;

public class YesOrNoConverter extends AbstractBeanField<Boolean, String> {
    @Override
    protected Boolean convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null; // Handle empty or null values
        }
        String trimmedValue = value.trim().toLowerCase();
        if ("sí".equals(trimmedValue) || "si".equals(trimmedValue)) {
            return true;
        } else if (("no".equalsIgnoreCase(trimmedValue)) || "no sabe o no contesta".equalsIgnoreCase(trimmedValue)) {
            return false;
        } else {
            throw new IllegalArgumentException("Valor no reconocido: " + value);
        }
    }
}
