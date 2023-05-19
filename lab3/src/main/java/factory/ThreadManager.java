package factory;

import factory.config.FactoryConfig;
import factory.controller.ThreadPoolController;
import factory.dealer.Dealer;
import factory.product.Accessory;
import factory.product.Body;
import factory.product.Engine;
import factory.supplier.Supplier;
import factory.wrapper.Wrapper;
import lombok.Getter;
import threadpool.TaskWorker;
import threadpool.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ThreadManager {
    private final FactoryConfig config;
    private final Wrapper<Boolean> loggerEnabled;
    private final StorageProvider storageProvider;
    private final DelayProvider delayProvider;

    private final ThreadPool carFactory;
    private final Supplier<Body> bodySupplier;
    private final Supplier<Engine> engineSupplier;
    private final List<Supplier<Accessory>> accessorySuppliers;
    private final List<Dealer> dealers;

    private final Object controllerLock;

    private final List<Thread> threads = new ArrayList<>();

    public ThreadManager(FactoryConfig config, StorageProvider storageProvider, DelayProvider delayProvider, Object controllerLock) {
        this.config = config;
        this.storageProvider = storageProvider;
        this.delayProvider = delayProvider;
        this.controllerLock = controllerLock;
        this.loggerEnabled = new Wrapper<>();
        loggerEnabled.setValue(config.isLoggingEnabled());
        carFactory = new ThreadPool(config.getThreadsNumber().get(TaskWorker.class.getName()));
        bodySupplier =
                new Supplier<>(Body.class, storageProvider.getBodyStorage(), delayProvider.getBodySupplierDelay());

        engineSupplier =
                new Supplier<>(Engine.class, storageProvider.getEngineStorage(), delayProvider.getEngineSupplierDelay());
        accessorySuppliers = initAccessorySuppliers();
        dealers = initDealers();
    }

    public void initThreads() {
        threads.add(new Thread(bodySupplier));
        threads.add(new Thread(engineSupplier));
        threads.addAll(accessorySuppliers.stream().map(Thread::new).collect(Collectors.toList()));
        threads.addAll(dealers.stream().map(Thread::new).collect(Collectors.toList()));
    }

    public void startThreads() {
        threads.forEach(Thread::start);
    }

    public void stopThreads() {
        carFactory.stop();
        threads.forEach(Thread::interrupt);
    }

    private List<Supplier<Accessory>> initAccessorySuppliers() {
        List<Supplier<Accessory>> list = new ArrayList<>();
        for (int i = 0; i < config.getThreadsNumber().get(Accessory.class.getName()); i++) {
            list.add(new Supplier<>(Accessory.class, storageProvider.getAccessoryStorage(), delayProvider.getAccessorySupplierDelay()));
        }
        return list;
    }

    private List<Dealer> initDealers() {
        List<Dealer> dealers = new ArrayList<>();
        for (int i = 0; i < config.getThreadsNumber().get(Dealer.class.getName()); i++) {
            dealers.add(new Dealer(storageProvider.getCarStorage(), delayProvider.getDealerDelay(), controllerLock, loggerEnabled));
        }
        return dealers;
    }
}
