package factory.product;

public class Accessory extends Product {
    @Override
    public String toString() {
        return String.format("Accessory:<%d>", id);
    }
}
