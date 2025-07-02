package Utils.ETL;

import Modelo.Encuesta;
import Modelo.EncuestaPersona;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransformerEncuesta implements Transformer<List<Encuesta>, List<Encuesta>>{

    /**
        El TransformerEncuesta es el encargado de transformar las encuestas de vivienda.
        Tambien de cargar las encuestas de personas y asociarlas a las encuestas de vivienda.
        @param path Ruta del archivo CSV de encuestas de personas.
        @param input Lista de Encuestas de vivienda a transformar.
        @return Lista de Encuestas transformadas, con las personas asociadas a cada encuesta de vivienda.
     **/


    String path;

    public TransformerEncuesta(String path) {
        this.path = path;
    }

    @Override
    public List<Encuesta> transform(List<Encuesta> input) {
        input = borrarEncuestasInvalidas(input);
        System.out.println("\n=== Iniciando Transformación ===");

        Map<String, List<EncuestaPersona>> personas = new TransformerEncuestaPersonas()
                .transform(new ExtractorEncuestaPersonas().extract(path));

        System.out.println("Total de mapeos de personas: " + personas.size());
        System.out.println("UUIDs disponibles en el mapa: " + personas.keySet());

        for(Encuesta encuesta : input) {
            String uuid = encuesta.getUuidApi();
            System.out.println("\nProcesando encuesta con UUID: " + uuid);

            List<EncuestaPersona> encuestaPersonas = personas.get(uuid);
            if (encuestaPersonas != null) {
                System.out.println("Encontradas " + encuestaPersonas.size() + " personas para UUID: " + uuid);
                encuesta.setPersonas(encuestaPersonas);
            } else {
                System.out.println("No se encontraron personas para UUID: " + uuid);
                encuesta.setPersonas(new ArrayList<>());
            }
        }

        return input;
    }
    public List<Encuesta> borrarEncuestasInvalidas(List<Encuesta> input) {
        List<Encuesta> validas = new ArrayList<>();
        for (Encuesta encuesta : input) {
            if (encuesta.getTipoVivienda() != null && !encuesta.getUuidApi().isEmpty()) {
                validas.add(encuesta);
            } else {
                System.out.println("Encuesta inválida detectada y eliminada: " + encuesta);
            }
        }
        System.out.println(validas.size());
        return validas;
    }

}
