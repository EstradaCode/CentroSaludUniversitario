package Utils.Converters;

import com.opencsv.bean.AbstractBeanField;

import java.time.Instant;
import java.util.Date;

public class IsoDateConverter extends AbstractBeanField<Date, String> {
    @Override
    protected Date convert(String value) {
            if(value != null) {
                Instant instant = Instant.parse(value);
                return Date.from(instant);
            }
            return null;
        }

}
