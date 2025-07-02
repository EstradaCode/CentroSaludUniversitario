
package Utils.ETL;

import Modelo.EncuestaPersona;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ExtractorEncuestaPersonas implements Extractor<List<EncuestaPersona>> {

    @Override
    public List<EncuestaPersona> extract(String path) throws IllegalArgumentException {
        try (Reader reader = new FileReader(path)) {
            CsvToBean<EncuestaPersona> csvToBean = new CsvToBeanBuilder<EncuestaPersona>(reader)
                    .withType(EncuestaPersona.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .withSeparator(',')
                    .withThrowExceptions(false) // This will collect errors instead of throwing immediately
                    .build();

            List<EncuestaPersona> personas = csvToBean.parse();
            for(EncuestaPersona encuesta : personas) {
                System.out.println("EncuestaPersona: " + encuesta);
            }
            // Check if there were any parse errors
            if (!csvToBean.getCapturedExceptions().isEmpty()) {
                StringBuilder errorMsg = new StringBuilder("Errores al procesar el CSV:\n");
                csvToBean.getCapturedExceptions().forEach(error ->
                        errorMsg.append("Línea ").append(error.getLineNumber())
                                .append(": ").append(error.getMessage()).append("\n")
                );
                throw new IllegalArgumentException(errorMsg.toString());
            }

            return personas;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al extraer EncuestaPersonas desde el archivo: " + path
                    + "\nDetalle: " + e.getMessage(), e);
        }
    }
    private void printEncuestaPersona(EncuestaPersona encuesta) {
        System.out.println("EncuestaPersona: " + encuesta);
    }
}
