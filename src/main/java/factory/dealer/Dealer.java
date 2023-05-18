package factory.dealer;


import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import factory.delay.Delay;
import factory.product.Car;
import factory.storage.Storage;
import factory.wrapper.Wrapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class Dealer implements Runnable {
    private static final AtomicInteger SEQ = new AtomicInteger(0);
    private static final Logger log = LoggerFactory.getLogger(Dealer.class);
    @Getter
    private final int id = SEQ.getAndIncrement();

    @NonNull
    private final Storage<Car> storage;
    @NonNull
    private final Delay delay;
    @NonNull
    private final Wrapper<Boolean> loggerEnabled;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Car car = storage.take();
            if (loggerEnabled.getValue()) {
                log.info(String.format("Dealer:<%d>%s", id, car));
            }
            try {
                Thread.sleep(delay.getValue());
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
