package factory;

import factory.config.FactoryConfig;
import factory.product.Accessory;
import factory.product.Body;
import factory.product.Car;
import factory.product.Engine;
import factory.storage.Storage;
import lombok.Getter;

@Getter
public class StorageProvider {
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;

    public StorageProvider(FactoryConfig config) {
        bodyStorage = new Storage<>(config.getStorageSize().get(Body.class.getName()));
        engineStorage = new Storage<>(config.getStorageSize().get(Engine.class.getName()));
        accessoryStorage = new Storage<>(config.getStorageSize().get(Accessory.class.getName()));
        carStorage = new Storage<>(config.getStorageSize().get(Car.class.getName()));
    }
}
