package Utils.ETL;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class CSVHeaderMapping<T> extends HeaderColumnNameMappingStrategy<T> {
    @Override
    public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
        String[] header = super.generateHeader(bean);
        // Remove any BOM characters from header
        for (int i = 0; i < header.length; i++) {
            if (header[i] != null) {
                header[i] = header[i].replaceAll("\\p{C}", "").trim();
            }
        }
        return header;
    }
}
