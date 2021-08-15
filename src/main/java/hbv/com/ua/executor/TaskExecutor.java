package hbv.com.ua.executor;

import hbv.com.ua.listener.ShutdownTaskExecutorListener;
import hbv.com.ua.runner.Runner;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 6:53 PM
 **/
public interface TaskExecutor {

    Runner runner();

    ExecutorService getExecutorService();

    <R> Future<R> submit(Callable<R> task);

    void submit(Runnable task);

    <R> List<Future<R>> invokeAll(Collection<? extends Callable<R>> tasks) throws InterruptedException;

    <R> List<Future<R>> invokeAllWithDefaultTimeout(Collection<? extends Callable<R>> tasks) throws InterruptedException;

    <R> List<Future<R>> invokeAll(Collection<? extends Callable<R>> tasks, long timeout, TimeUnit timeUnit) throws InterruptedException;

    <R> R invokeAny(Collection<? extends Callable<R>> tasks) throws ExecutionException, InterruptedException;

    <R> R invokeAnyWithDefaultTimeout(Collection<? extends Callable<R>> tasks) throws ExecutionException, InterruptedException, TimeoutException;

    <R> R invokeAny(Collection<? extends Callable<R>> tasks, long timeout, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException;

    default void registerShutdownListener(ShutdownTaskExecutorListener listener) {
        Runtime.getRuntime().addShutdownHook(new Thread(listener));
    }
}
