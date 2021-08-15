package hbv.com.ua.observer.adapter;

import hbv.com.ua.observer.Observer;

import java.util.concurrent.Callable;

/**
 * author: viacheslavbondarchuk
 * date: 8/15/21
 * time: 6:09 PM
 **/
public class ObservableAdapter<D> implements Callable<D> {
    private final Callable<D> callable;
    private final Observer<D> observer;

    public ObservableAdapter(Callable<D> callable, Observer<D> observer) {
        this.callable = callable;
        this.observer = observer;
    }

    @Override
    public D call() throws Exception {
        observer.update(callable.call());
        return null;
    }
}
