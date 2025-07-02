package Utils.Converters;

import com.opencsv.bean.AbstractBeanField;

public class OwnerUuidConverter extends AbstractBeanField<String, String> {
    @Override
    protected String convert(String value) {
        if (value == null) return null;
        String cleanValue = removeBOM(value.trim());
        if (cleanValue.isEmpty() || cleanValue.equalsIgnoreCase("ec5_branch_owner_uuid") || cleanValue.equalsIgnoreCase("ec5_uuid")) return null;
        return cleanValue;
    }
    private String removeBOM(String value) {
        if (value.startsWith("\uFEFF")) { // UTF-8 BOM
            return value.substring(1);
        }
        if (value.startsWith("\uFFFE") || value.startsWith("\uFEFF")) { // UTF-16 BOMs
            return value.substring(1);
        }
        if (value.length() > 1 && (value.charAt(0) == 0xFF && value.charAt(1) == 0xFE)) { // UTF-16 LE BOM
            return value.substring(2);
        }
        if (value.length() > 1 && (value.charAt(0) == 0xFE && value.charAt(1) == 0xFF)) { // UTF-16 BE BOM
            return value.substring(2);
        }
        return value;
    }


}
