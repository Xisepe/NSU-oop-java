package threadpool;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {
    private final AtomicInteger completedTasks = new AtomicInteger(0);
    private final List<TaskWorker> workers;
    private final Queue<Runnable> tasksQueue;
    private boolean isStopped;

    public ThreadPool(int numberOfWorkers) {
        this.workers = new ArrayList<>(numberOfWorkers);
        this.tasksQueue = new ArrayDeque<>();
        initializeWorkers(numberOfWorkers);
        startWorkers();
    }

    private void initializeWorkers(int numberOfWorkers) {
        for (int i = 0; i < numberOfWorkers; i++) {
            this.workers.add(new TaskWorker(completedTasks, tasksQueue));
        }
    }

    private void startWorkers() {
        this.workers.forEach(e -> new Thread(e).start());
    }

    public synchronized void stop() {
        this.isStopped = true;
        workers.forEach(TaskWorker::stop);
    }

    public void execute(Runnable command) {
        synchronized (tasksQueue) {
            if (isStopped) {
                return;
            }
            tasksQueue.offer(command);
            tasksQueue.notify();
        }
    }

    public int getCurrentSize() {
        return tasksQueue.size();
    }

    public int getCompletedTasks() {
        return completedTasks.get();
    }

}
