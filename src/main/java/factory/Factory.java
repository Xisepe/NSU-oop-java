package factory;

import factory.config.FactoryConfig;
import factory.controller.ThreadPoolController;
import factory.wrapper.Wrapper;
import lombok.Getter;

@Getter
public class Factory {
    private final FactoryConfig config = FactoryConfig.loadConfig("config.properties");
    private final Wrapper<Boolean> loggerEnabled = new Wrapper<Boolean>() {{
        setValue(config.isLoggingEnabled());
    }};

    private final StorageProvider storageProvider = new StorageProvider(config);
    private final DelayProvider delayProvider = new DelayProvider(config);
    private final Object controllerLock = new Object();
    private final ThreadManager threadManager = new ThreadManager(config, storageProvider, delayProvider, controllerLock){{
        initThreads();
    }};
    private final ThreadPoolController threadPoolController = new ThreadPoolController(
            threadManager.getCarFactory(),
            storageProvider.getBodyStorage(), storageProvider.getEngineStorage(), storageProvider.getAccessoryStorage(),
            storageProvider.getCarStorage(), controllerLock
    );

    private final Thread controllerThread = new Thread(threadPoolController);

    public void start() {
        threadManager.startThreads();
        controllerThread.start();
    }

    public void stop() {
        threadManager.stopThreads();
        controllerThread.interrupt();
    }

}
