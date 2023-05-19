package factory.controller;

import factory.product.Accessory;
import factory.product.Body;
import factory.product.Car;
import factory.product.Engine;
import factory.storage.Storage;
import factory.task.CarBuildTask;
import lombok.RequiredArgsConstructor;
import threadpool.ThreadPool;


@RequiredArgsConstructor
public class ThreadPoolController implements Runnable {
    private final ThreadPool factory;
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;
    private final Object lock;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (lock) {
                while (!carStorage.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                factory.execute(new CarBuildTask(bodyStorage, engineStorage, accessoryStorage, carStorage));
            }
        }
    }
}
