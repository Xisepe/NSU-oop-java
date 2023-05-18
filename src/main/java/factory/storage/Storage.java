package factory.storage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Storage<T> {
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
        return values.poll();

    }

    public synchronized boolean isFull() {
        return values.size() == capacity;
    }

    public synchronized boolean isEmpty() {
        return values.isEmpty();
    }


}
