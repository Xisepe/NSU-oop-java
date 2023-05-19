package factory.supplier;

import factory.delay.Delay;
import factory.storage.Storage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class Supplier<T> implements Runnable {
    @NonNull
    private final Class<T> type;
    @NonNull
    private final Storage<T> storage;
    @NonNull
    private final Delay delay;

    @SneakyThrows
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            T t = type.getConstructor().newInstance();
            storage.store(t);
            try {
                Thread.sleep(delay.getValue());
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
