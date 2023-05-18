package factory.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Car extends Product {
    private final Body body;
    private final Engine engine;
    private final Accessory accessory;

    @Override
    public String toString() {
        return String.format("Auto<%d>(%s,%s,%s)", id, body, engine, accessory);
    }

}
