package hbv.com.ua.executor.impl;

import hbv.com.ua.configuration.impl.ObservableThreadPollTaskExecutorConfiguration;
import hbv.com.ua.executor.ObservableThreadPoolTaskExecutor;
import hbv.com.ua.observer.Observer;
import hbv.com.ua.observer.adapter.ObservableAdapter;
import hbv.com.ua.runner.ObservableRunner;
import hbv.com.ua.runner.Runner;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 9:08 PM
 **/
public class ObservableThreadPoolTaskExecutorImpl implements ObservableThreadPoolTaskExecutor {
    private final ObservableThreadPollTaskExecutorConfiguration observableThreadPollTaskExecutorConfiguration;
    private ExecutorService executorService;

    public ObservableThreadPoolTaskExecutorImpl(ObservableThreadPollTaskExecutorConfiguration observableThreadPollTaskExecutorConfiguration) {
        this.observableThreadPollTaskExecutorConfiguration = observableThreadPollTaskExecutorConfiguration;
        this.init();
    }

    private void init() {
        this.executorService = Executors.newFixedThreadPool(
                observableThreadPollTaskExecutorConfiguration.getThreads(),
                Executors.defaultThreadFactory());
    }

    @Override
    public ObservableRunner runner() {
        return new ObservableRunnerImpl();
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

    @Override
    public <R> List<Future<R>> invokeAll(Collection<? extends Callable<R>> tasks) throws InterruptedException {
        return executorService.invokeAll(tasks);
    }

    @Override
    public <R> List<Future<R>> invokeAllWithDefaultTimeout(Collection<? extends Callable<R>> tasks) throws InterruptedException {
        return executorService.invokeAll(tasks,
                observableThreadPollTaskExecutorConfiguration.getTimeout(),
                observableThreadPollTaskExecutorConfiguration.getTimeUnit());
    }

    @Override
    public <R> List<Future<R>> invokeAll(Collection<? extends Callable<R>> tasks, long timeout, TimeUnit timeUnit) throws InterruptedException {
        return executorService.invokeAll(tasks, timeout, timeUnit);
    }

    @Override
    public <R> R invokeAny(Collection<? extends Callable<R>> tasks) throws ExecutionException, InterruptedException {
        return executorService.invokeAny(tasks);
    }

    @Override
    public <R> R invokeAnyWithDefaultTimeout(Collection<? extends Callable<R>> tasks) throws ExecutionException, InterruptedException, TimeoutException {
        return executorService.invokeAny(tasks,
                observableThreadPollTaskExecutorConfiguration.getTimeout(),
                observableThreadPollTaskExecutorConfiguration.getTimeUnit());
    }

    @Override
    public <R> R invokeAny(Collection<? extends Callable<R>> tasks, long timeout, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return executorService.invokeAny(tasks, timeout, timeUnit);
    }

    public <D> void submit(Callable<D> task, Observer<D> observer) {
        executorService.submit(new ObservableAdapter<D>(task, observer));
    }

    public <D> void submit(Callable<D> task, Collection<Observer<D>> observers) {
        executorService.submit(new ObservableAdapter<D>(task, (data) -> observers.forEach(observer -> observer.update(data))));
    }

    public <D> void invokeAll(Collection<Callable<D>> tasks, Observer<D> observer) {
        tasks.forEach(task -> executorService.submit(new ObservableAdapter<D>(task, observer)));
    }

    public final class ObservableRunnerImpl implements ObservableRunner {

        public ObservableRunnerImpl submit(Runnable task) {
            ObservableThreadPoolTaskExecutorImpl.this.submit(task);
            return this;
        }

        public <D> ObservableRunner submit(Callable<D> task, Observer<D> observer) {
            ObservableThreadPoolTaskExecutorImpl.this.submit(new ObservableAdapter<D>(task, observer));
            return this;
        }

        public <D> ObservableRunner submit(Callable<D> task, Collection<Observer<D>> observers) {
            ObservableThreadPoolTaskExecutorImpl.this.submit(new ObservableAdapter<D>(task, (data) -> observers.forEach(observer -> observer.update(data))));
            return this;
        }

        public <D> ObservableRunner invokeAll(Collection<Callable<D>> tasks, Observer<D> observer) {
            tasks.forEach(task -> ObservableThreadPoolTaskExecutorImpl.this.executorService.submit(new ObservableAdapter<D>(task, observer)));
            return this;
        }
    }
}
