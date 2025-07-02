package Utils.ETL;

import Modelo.Encuesta;
import Servicios.Negocio.EncuestaService;
import Utils.EntityMgmt;
import jakarta.persistence.EntityManager;

import java.net.URL;
import java.util.List;

public class LoaderEncuesta implements Loader<List<Encuesta>>{


    private EncuestaService encuestaService;
    public LoaderEncuesta() {
        EntityManager em = EntityMgmt.getEntityManager();
        this.encuestaService = new EncuestaService(em); // Aquí deberías pasar el EntityManager adecuado
    }
    @Override
    public void load(List<Encuesta> data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("La lista de encuestas no puede ser nula o vacía");
        }

        encuestaService.crearEncuestas(data);
    }
}
