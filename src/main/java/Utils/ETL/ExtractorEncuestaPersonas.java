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
                    .build();
            return csvToBean.parse();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al extraer EncuestaPersonas desde el archivo: " + path, e);
        }
    }

}
