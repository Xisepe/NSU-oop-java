package threadpool;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
class TaskWorker implements Runnable {
    private final AtomicInteger completedTasks;
    private final BlockingQueue<Runnable> tasksQueue;
    private Thread thread;
    private volatile boolean isStopped;


    public synchronized boolean isStopped() {
        return isStopped;
    }

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
        while (!isStopped) {
            try {
                Runnable task = tasksQueue.take();
                task.run();
                completedTasks.incrementAndGet();
            } catch (InterruptedException e) {
            }
        }
    }
}