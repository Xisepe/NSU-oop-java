package factory.product;

import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private static final AtomicInteger SEQ = new AtomicInteger();
    protected final int id = SEQ.getAndIncrement();

    public int getId() {
        return id;
    }
}
