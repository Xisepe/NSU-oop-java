package factory.product;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private static final AtomicInteger SEQ = new AtomicInteger();
    @Getter
    protected final int id = SEQ.getAndIncrement();
}
