package threadpool;

import lombok.RequiredArgsConstructor;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class TaskWorker implements Runnable {
    private final Queue<Runnable> tasksQueue;
    private Thread thread;
    private volatile boolean isStopped;

    public synchronized void stop() {
        if (isStopped) {
            return;
        }
        this.isStopped = true;
        this.thread.interrupt();
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        synchronized (tasksQueue) {
            while (!isStopped) {
                while (tasksQueue.isEmpty()) {
                    try {
                        tasksQueue.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                Runnable task = tasksQueue.poll();
                task.run();
            }
        }
    }
}