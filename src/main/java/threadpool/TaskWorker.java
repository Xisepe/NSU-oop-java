package threadpool;

import java.util.concurrent.BlockingQueue;


class TaskWorker implements Runnable {
    private final BlockingQueue<Runnable> tasksQueue;
    private Thread thread;
    private volatile boolean isStopped;

    public TaskWorker(BlockingQueue<Runnable> tasksQueue) {
        this.tasksQueue = tasksQueue;
    }

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
            } catch (InterruptedException e) {

            }
        }
    }
}