package hbv.com.ua.executor;

import hbv.com.ua.listener.ValuableListener;
import hbv.com.ua.listener.ValuelessListener;
import hbv.com.ua.runner.ListenableRunner;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 4:45 PM
 **/
public interface ListenableThreadPoolTaskExecutor extends TaskExecutor {

    <R> void invokeAny(Collection<? extends Callable<R>> tasks, ValuableListener<R> listener);

    @Override
    ListenableRunner runner();

    <R> void submit(Callable<R> task, ValuableListener<R> listener);

    void submit(Runnable task, ValuelessListener listener);

    <R> void invokeAll(Collection<? extends Callable<R>> tasks, ValuableListener<R> listener);
}
