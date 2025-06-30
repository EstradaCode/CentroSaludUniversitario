package Utils.Converters;

import Utils.Enums.TipoEmpleo;
import com.opencsv.bean.AbstractBeanField;

public class TipoEmpleoConverter extends AbstractBeanField<TipoEmpleo, String> {
    @Override
    protected TipoEmpleo convert(String value) {
        return TipoEmpleo.fromString(value);
    }
}
