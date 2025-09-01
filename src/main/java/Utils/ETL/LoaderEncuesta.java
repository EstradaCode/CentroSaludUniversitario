package Utils.ETL;

import Modelo.Encuesta;
import Servicios.Implementacion.EncuestaService;

import java.util.List;

public class LoaderEncuesta implements Loader<List<Encuesta>>{


    private EncuestaService encuestaService;

    @Override
    public void load(List<Encuesta> data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("La lista de encuestas no puede ser nula o vacía");
        }

        encuestaService.crearEncuestas(data);
    }
}
