package Utils;

import Modelo.Encuesta;

import java.util.ArrayList;
import java.util.List;

import Modelo.EncuestaPersona;
import com.opencsv.CSVReader;
/*
 * No se recorrieron los archivos de forma condicional con corte de control. Porque el orden de los archivos puede variar.
 */
public class CSVReader {
    /**
     * Método para leer un archivo CSV y convertirlo en una lista de Encuestas.
     * Este método es un stub y debe ser implementado según las necesidades específicas.
     *
     * @param mainCSV Ruta del archivo CSV de encuestas de vivienta.
     * @param secondaryCSV Ruta del archivo CSV de las encuestas por persona.
     * @return Lista de Encuestas leídas del archivo CSV.
     */
    public static List<Encuesta> runCSVReader(String mainCSV, String secondaryCSV) {
        // Primero leer el archivo de encuestas por persona
        // Agregar el uuid a un set para evitar duplicados
        // Despues obtener todas las encuestas de la misma vivienda
        // Buscar por el uuid la encuesta principal. Y agregar las personas a la encuesta

        return new ArrayList<>();
    }
    private static Encuesta transformEncuesta(List<EncuestaPersona> personas, String[] mainRow) {
        // Transformar la fila del CSV en una instancia de Encuesta
        // Asignar las personas a la encuesta
        return new Encuesta();
    }
    private static List<String[]> readEncuestasPersonas(String filePath) {

    }
}