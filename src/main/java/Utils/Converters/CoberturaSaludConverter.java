package Utils.Converters;

import Utils.Enums.CoberturaSalud;
import com.opencsv.bean.AbstractBeanField;

public class CoberturaSaludConverter extends AbstractBeanField<CoberturaSalud, String> {
    @Override
    protected CoberturaSalud convert(String value) {
        return CoberturaSalud.fromString(value);
    }
}
