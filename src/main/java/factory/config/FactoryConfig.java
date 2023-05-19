package factory.config;

import factory.dealer.Dealer;
import factory.product.Accessory;
import factory.product.Body;
import factory.product.Car;
import factory.product.Engine;
import factory.supplier.Supplier;
import lombok.Data;
import threadpool.TaskWorker;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Data
public class FactoryConfig {
    private final Map<String, Integer> storageSize = new HashMap<>();
    private final Map<String, Integer> threadsNumber = new HashMap<>();
    private boolean loggingEnabled;

    public static FactoryConfig loadConfig(String name) {
        if (name == null || name.isEmpty()) {
            name = "config.properties";
        }
        InputStream resource = FactoryConfig.class.getClassLoader().getResourceAsStream(name);
        Properties properties = new Properties();
        FactoryConfig factoryConfig = new FactoryConfig();
        try {
            properties.load(resource);
        } catch (IOException e) {
            System.err.println("Loading default config");
        }
        initFactoryConfig(factoryConfig, properties);
        return factoryConfig;
    }

    private static void initFactoryConfig(FactoryConfig factoryConfig, Properties properties) {
        factoryConfig.loggingEnabled = Boolean.parseBoolean(properties.getProperty("logging", "true"));
        factoryConfig.storageSize.put(
                Body.class.getName(),
                Integer.parseInt(
                        properties.getProperty("storage.body.size", "100")
                )
        );
        factoryConfig.storageSize.put(
                Engine.class.getName(),
                Integer.parseInt(
                        properties.getProperty("storage.engine.size", "100")
                )
        );
        factoryConfig.storageSize.put(
                Accessory.class.getName(),
                Integer.parseInt(
                        properties.getProperty("storage.accessory.size", "100")
                )
        );
        factoryConfig.storageSize.put(
                Car.class.getName(),
                Integer.parseInt(
                        properties.getProperty("storage.car.size", "100")
                )
        );
        factoryConfig.threadsNumber.put(
                Accessory.class.getName(),
                Integer.parseInt(
                        properties.getProperty("threads.accessory.number", "5")
                )
        );
        factoryConfig.threadsNumber.put(
                TaskWorker.class.getName(),
                Integer.parseInt(
                        properties.getProperty("threads.worker.number", "5")
                )
        );
        factoryConfig.threadsNumber.put(
                Dealer.class.getName(),
                Integer.parseInt(
                        properties.getProperty("threads.dealer.number", "5")
                )
        );
    }
}
