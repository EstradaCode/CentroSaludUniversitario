package Utils.ETL;

public interface Loader<T> {
    /**
     * Carga un valor de tipo T en el sistema.
     *
     * @param value El valor a cargar.
     * @throws IllegalArgumentException Si el valor no es válido o no se puede cargar.
     */
    void load(T value) throws IllegalArgumentException;
}
