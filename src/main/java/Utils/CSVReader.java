package Utils;

import Modelo.Encuesta;

import java.util.ArrayList;
import java.util.List;

import Modelo.EncuestaPersona;
import Utils.ETL.ExtractorEncuesta;
import Utils.ETL.LoaderEncuesta;
import Utils.ETL.TransformerEncuesta;
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
    public static void runCSVReader(String mainCSV, String secondaryCSV) {
        List<Encuesta> encuestas = new ExtractorEncuesta().extract(mainCSV);
        List<Encuesta> transEncuestas =  (new TransformerEncuesta(secondaryCSV)).transform(encuestas);
        new LoaderEncuesta().load(transEncuestas);
    }

}