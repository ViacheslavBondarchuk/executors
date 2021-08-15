package hbv.com.ua.listener.adapter;

import hbv.com.ua.listener.ValuableListener;

import java.util.concurrent.Callable;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 6:02 PM
 **/

public class ValuableListenerAdapter<T> implements Callable<T> {
    private final ValuableListener<T> valuableListener;
    private final Callable<T> callback;

    public ValuableListenerAdapter(Callable<T> callback, ValuableListener<T> valuableListener) {
        this.valuableListener = valuableListener;
        this.callback = callback;
    }

    @Override
    public T call() throws Exception {
        valuableListener.execute(callback.call());
        return null;
    }
}
