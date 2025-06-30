package Utils.Converters;

import Utils.Enums.NivelEducativo;
import com.opencsv.bean.AbstractBeanField;

public class NivelEducativoConverter extends AbstractBeanField <NivelEducativo, String> {

    @Override
    protected NivelEducativo convert(String value) {
        return NivelEducativo.fromString(value);
    }
}
