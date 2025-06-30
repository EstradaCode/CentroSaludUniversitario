package Utils.ETL;

public interface Extractor<T> {
    /**
     * Extrae un valor de tipo T a partir de una cadena de texto.
     *
     * @param path La cadena de texto a procesar.
     * @return El valor extraído de tipo T.
     * @throws IllegalArgumentException Si el valor no es válido o no se puede extraer.
     */
    T extract(String path) throws IllegalArgumentException;
}
