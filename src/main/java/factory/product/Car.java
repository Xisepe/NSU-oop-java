package factory.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Car extends Product {
    private final Body body;
    private final Engine engine;
    private final Accessory accessory;
}
