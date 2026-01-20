
package Utils.ETL;

import Modelo.Encuesta;
import Utils.CSVHeaderMapping;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ExtractorEncuesta implements Extractor<List<Encuesta>> {

    @Override
    public List<Encuesta> extract(String path) throws IllegalArgumentException {
        try (Reader reader = new FileReader(path)) {
            CsvToBean<Encuesta> csvToBean = new CsvToBeanBuilder<Encuesta>(reader)
                    .withType(Encuesta.class)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Encuesta> a = csvToBean.parse();
            for(Encuesta encuesta : a) {
                System.out.println("Encuesta: " + encuesta);
            }
            return a;

        } catch (Exception e) {
            throw new RuntimeException("Error al leer el archivo CSV", e);
        }
    }
}