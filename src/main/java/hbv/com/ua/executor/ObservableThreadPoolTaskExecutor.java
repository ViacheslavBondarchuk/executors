package hbv.com.ua.executor;

import hbv.com.ua.executor.impl.ObservableThreadPoolTaskExecutorImpl;
import hbv.com.ua.observer.Observer;
import hbv.com.ua.runner.ObservableRunner;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 9:08 PM
 **/
public interface ObservableThreadPoolTaskExecutor extends TaskExecutor{

    @Override
    ObservableRunner runner();

    <D> void submit(Callable<D> task, Observer<D> observer);

    <D> void submit(Callable<D> task, Collection<Observer<D>> observers);

    <D> void invokeAll(Collection<Callable<D>> tasks, Observer<D> observer);
}
