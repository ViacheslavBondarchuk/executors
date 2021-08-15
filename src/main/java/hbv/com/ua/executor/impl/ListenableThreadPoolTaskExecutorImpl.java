package hbv.com.ua.executor.impl;

import hbv.com.ua.configuration.impl.ListenableThreadPoolTaskExecutorConfiguration;
import hbv.com.ua.executor.ListenableThreadPoolTaskExecutor;
import hbv.com.ua.listener.ValuableListener;
import hbv.com.ua.listener.ValuelessListener;
import hbv.com.ua.listener.adapter.ValuableListenerAdapter;
import hbv.com.ua.listener.adapter.ValuelessListenerAdapter;
import hbv.com.ua.runner.ListenableRunner;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 4:49 PM
 **/
public class ListenableThreadPoolTaskExecutorImpl implements ListenableThreadPoolTaskExecutor {
    private final ListenableThreadPoolTaskExecutorConfiguration listenableThreadPoolTaskExecutorConfiguration;
    private ExecutorService executorService;

    public ListenableThreadPoolTaskExecutorImpl(ListenableThreadPoolTaskExecutorConfiguration listenableThreadPoolTaskExecutorConfiguration) {
        this.listenableThreadPoolTaskExecutorConfiguration = listenableThreadPoolTaskExecutorConfiguration;
        this.init();
    }

    private void init() {
        this.executorService = Executors.newFixedThreadPool(
                listenableThreadPoolTaskExecutorConfiguration.getThreads(),
                Executors.defaultThreadFactory());
    }

    @Override
    public ListenableRunner runner() {
        return new ListenableRunnerImpl();
    }

    @Override
    public ExecutorService getExecutorService() {
        return executorService;
    }

    @Override
    public <R> Future<R> submit(Callable<R> task) {
        return executorService.submit(task);
    }

    @Override
    public void submit(Runnable task) {
        executorService.submit(task);
    }

    public <R> void submit(Callable<R> task, ValuableListener<R> valuableListener) {
        executorService.submit(new ValuableListenerAdapter<>(task, valuableListener));
    }

    public void submit(Runnable task, ValuelessListener valuableListener) {
        executorService.submit(new ValuelessListenerAdapter(task, valuableListener));
    }

    public <R> List<Future<R>> invokeAll(Collection<? extends Callable<R>> tasks) throws InterruptedException {
        return executorService.invokeAll(tasks);
    }

    public <R> List<Future<R>> invokeAllWithDefaultTimeout(Collection<? extends Callable<R>> tasks) throws InterruptedException {
        return executorService.invokeAll(tasks,
                listenableThreadPoolTaskExecutorConfiguration.getTimeout(),
                listenableThreadPoolTaskExecutorConfiguration.getTimeUnit());
    }

    public <R> List<Future<R>> invokeAll(Collection<? extends Callable<R>> tasks, long timeout, TimeUnit timeUnit) throws InterruptedException {
        return executorService.invokeAll(tasks, timeout, timeUnit);
    }

    public <R> void invokeAll(Collection<? extends Callable<R>> tasks, ValuableListener<R> listener) {
        tasks.forEach((task) -> executorService.submit(new ValuableListenerAdapter<>(task, listener)));
    }

    public <R> R invokeAny(Collection<? extends Callable<R>> tasks) throws ExecutionException, InterruptedException {
        return executorService.invokeAny(tasks);
    }

    public <R> R invokeAnyWithDefaultTimeout(Collection<? extends Callable<R>> tasks) throws ExecutionException, InterruptedException, TimeoutException {
        return executorService.invokeAny(tasks,
                listenableThreadPoolTaskExecutorConfiguration.getTimeout(),
                listenableThreadPoolTaskExecutorConfiguration.getTimeUnit());
    }

    public <R> R invokeAny(Collection<? extends Callable<R>> tasks, long timeout, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return executorService.invokeAny(tasks, timeout, timeUnit);
    }

    public <R> void invokeAny(Collection<? extends Callable<R>> tasks, ValuableListener<R> listener) {
        this.invokeAll(tasks, listener);
    }

    public final class ListenableRunnerImpl implements ListenableRunner {

        public <R> ListenableRunner submit(Callable<R> task, ValuableListener<R> listener) {
            ListenableThreadPoolTaskExecutorImpl.this.submit(task, listener);
            return this;
        }

        public ListenableRunnerImpl submit(Runnable task, ValuelessListener listener) {
            ListenableThreadPoolTaskExecutorImpl.this.submit(task, listener);
            return this;
        }

        public ListenableRunnerImpl submit(Runnable task) {
            ListenableThreadPoolTaskExecutorImpl.this.submit(task);
            return this;
        }

        public <R> ListenableRunnerImpl invokeAll(Collection<? extends Callable<R>> tasks, ValuableListener<R> listener) {
            tasks.forEach(ListenableThreadPoolTaskExecutorImpl.this::submit);
            return this;
        }

        public <R> ListenableRunnerImpl invokeAny(Collection<? extends Callable<R>> tasks, ValuableListener<R> listener) {
            this.invokeAll(tasks, listener);
            return this;
        }
    }


}
