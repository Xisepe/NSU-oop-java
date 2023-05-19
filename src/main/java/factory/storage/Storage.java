package factory.storage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage<T> {
    private final AtomicInteger totalDetails = new AtomicInteger(0);
    private final AtomicInteger currentDetails = new AtomicInteger(0);
    private final int capacity;
    private final BlockingQueue<T> values;

    public Storage(int capacity) {
        this.capacity = capacity;
        this.values = new ArrayBlockingQueue<>(capacity);
    }

    public synchronized void store(T value) {
        while (isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        values.offer(value);
        totalDetails.incrementAndGet();
        currentDetails.incrementAndGet();
        notify();
    }

    public synchronized T take() {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        notify();
        currentDetails.decrementAndGet();
        return values.poll();

    }

    public synchronized boolean isFull() {
        return values.size() == capacity;
    }

    public synchronized boolean isEmpty() {
        return values.isEmpty();
    }

    public int getTotalDetails() {
        return totalDetails.get();
    }

    public int getCurrentDetails() {
        return currentDetails.get();
    }
}
