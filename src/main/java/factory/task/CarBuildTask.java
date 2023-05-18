package factory.task;

import factory.product.Accessory;
import factory.product.Body;
import factory.product.Car;
import factory.product.Engine;
import factory.storage.Storage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CarBuildTask implements Runnable {
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;

    @Override
    public void run() {
        Body body = bodyStorage.take();
        Engine engine = engineStorage.take();
        Accessory accessory = accessoryStorage.take();
        Car car = new Car(body, engine, accessory);
        carStorage.store(car);
    }
}
