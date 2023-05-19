package factory.product;

public class Body extends Product {
    @Override
    public String toString() {
        return String.format("Body:<%d>", id);
    }
}
