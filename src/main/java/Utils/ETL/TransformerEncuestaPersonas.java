package Utils.ETL;

import Modelo.EncuestaPersona;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// El transformer devuelve un mapa con la clave de ownerUuid y el valor una lista de EncuestaPersona asociada a ese UUID.
public class TransformerEncuestaPersonas implements Transformer<List<EncuestaPersona>,Map<String,List<EncuestaPersona>>>{

    @Override
    public Map<String,List<EncuestaPersona>> transform(List<EncuestaPersona> input) {

        Map<String, List<EncuestaPersona>> map = new HashMap<String, List<EncuestaPersona>>();

        for(EncuestaPersona encuesta : input) {
            String id = encuesta.getOwnerUuid(); // Asegurarse de que el UUID esté presente
            if (id != null) {
                if (!map.containsKey(id)) {
                    map.put(id, new ArrayList<EncuestaPersona>());
                }
                map.get(id).add(encuesta);
            } else {
                throw new IllegalArgumentException("EncuestaPersona con ownerUuid nulo: " + encuesta);
            }
        }
        return map;
    }
}
