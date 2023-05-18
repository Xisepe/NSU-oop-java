package factory.product;

public class Engine extends Product {
    @Override
    public String toString() {
        return String.format("Engine:<%d>", id);
    }
}
