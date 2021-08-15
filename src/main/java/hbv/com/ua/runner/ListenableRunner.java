package hbv.com.ua.runner;

import hbv.com.ua.listener.ValuableListener;
import hbv.com.ua.listener.ValuelessListener;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * author: viacheslavbondarchuk
 * date: 8/15/21
 * time: 6:32 PM
 **/
public interface ListenableRunner extends Runner {
    <R> ListenableRunner invokeAll(Collection<? extends Callable<R>> tasks, ValuableListener<R> listener);

    <R> ListenableRunner invokeAny(Collection<? extends Callable<R>> tasks, ValuableListener<R> listener);

    <R> ListenableRunner submit(Callable<R> task, ValuableListener<R> listener);

    ListenableRunner submit(Runnable task, ValuelessListener listener);
}
