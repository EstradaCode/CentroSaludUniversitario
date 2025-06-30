package Utils.Converters;

import Utils.Enums.Genero;
import com.opencsv.bean.AbstractBeanField;

public class GeneroConverter extends AbstractBeanField<Genero,String> {
    protected Genero convert(String value) {
        return Genero.fromString(value);
    }
}

