package Utils.Converters;

import Utils.Enums.CantidadHabitaciones;
import com.opencsv.bean.AbstractBeanField;

public class CantidadHabitacionesConverter extends AbstractBeanField<CantidadHabitaciones, String> {

    @Override
    protected CantidadHabitaciones convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return CantidadHabitaciones.NS_NC; // Retorna NS_NC si el valor es nulo o vacío
        }
        return CantidadHabitaciones.fromString(value);
    }
}
