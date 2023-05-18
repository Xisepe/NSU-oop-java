package factory.supplier;

import factory.delay.Delay;
import factory.storage.Storage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class Supplier<T> implements Runnable{
    private static final AtomicInteger produced = new AtomicInteger(0);
    @NonNull
    private final Class<T> type;
    @NonNull
    private final Storage<T> storage;
    @NonNull
    private final Delay delay;

    public static int getProduced() {
        return produced.get();
    }

    @SneakyThrows
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            T t = type.getConstructor().newInstance();
            storage.store(t);
            produced.incrementAndGet();
            try {
                Thread.sleep(delay.getValue());
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
