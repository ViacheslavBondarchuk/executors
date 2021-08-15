package hbv.com.ua.runner;

import hbv.com.ua.observer.Observer;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * author: viacheslavbondarchuk
 * date: 8/15/21
 * time: 6:32 PM
 **/
public interface ObservableRunner extends Runner {
    <D> ObservableRunner submit(Callable<D> task, Observer<D> observer);

    <D> ObservableRunner submit(Callable<D> task, Collection<Observer<D>> observers);

    <D> ObservableRunner invokeAll(Collection<Callable<D>> tasks, Observer<D> observer);
}
