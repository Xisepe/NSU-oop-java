package threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool implements Executor {
    private final List<Thread> threads = new ArrayList<>();
    private final BlockingQueue<Runnable> tasksQueue = new LinkedBlockingQueue<>();
    private volatile boolean running = true;

    public ThreadPool(int size) {
        initializeThreads(size);
        startThreads();
    }

    @Override
    public void execute(Runnable command) {
        if (!running) {
            return;
        }
        try {
            tasksQueue.put(command);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        running = false;
    }

    private void initializeThreads(int size) {
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(new TaskWorker()));
        }
    }

    private void startThreads() {
        threads.forEach(Thread::start);
    }

    private class TaskWorker implements Runnable {
        @Override
        public void run() {
            while (running) {
                Runnable task = tasksQueue.poll();
                if (task != null) {
                    task.run();
                }
            }
        }
    }

}
