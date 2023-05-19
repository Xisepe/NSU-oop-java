package threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {
    private final AtomicInteger completedTasks = new AtomicInteger(0);
    private final List<TaskWorker> workers;
    private final BlockingQueue<Runnable> tasksQueue;
    private volatile boolean isStopped = true;

    public ThreadPool(int numberOfWorkers) {
        this.workers = new ArrayList<>(numberOfWorkers);
        this.tasksQueue = new LinkedBlockingQueue<>();
        initializeWorkers(numberOfWorkers);
        startWorkers();
    }

    private void initializeWorkers(int numberOfWorkers) {
        for (int i = 0; i < numberOfWorkers; i++) {
            this.workers.add(new TaskWorker(completedTasks, tasksQueue));
        }
    }

    private void startWorkers() {
        isStopped = false;
        this.workers.forEach(e -> new Thread(e).start());
    }

    public synchronized void stop() {
        this.isStopped = true;
        waitForTasksCompletion();
        workers.forEach(TaskWorker::stop);
    }

    public synchronized void waitForTasksCompletion() {
        while (!tasksQueue.isEmpty()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }

    public synchronized void execute(Runnable command) {
        if (isStopped) {
            throw new IllegalStateException("Thread pool is stopped");
        }
        tasksQueue.offer(command);
    }

    public int getCurrentSize() {
        return tasksQueue.size();
    }

    public int getCompletedTasks() {
        return completedTasks.get();
    }

}
