package Utils.ETL;

public interface Transformer<I, O> {

    O transform(I input);
}
