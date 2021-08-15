package hbv.com.ua.listener.adapter;

import hbv.com.ua.listener.ValuelessListener;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 6:10 PM
 **/
public class ValuelessListenerAdapter implements Runnable {
    private final ValuelessListener valuelessListener;
    private final Runnable runnable;

    public ValuelessListenerAdapter(Runnable runnable, ValuelessListener valuelessListener) {
        this.runnable = runnable;
        this.valuelessListener = valuelessListener;
    }

    @Override
    public void run() {
        runnable.run();
        valuelessListener.execute();
    }
}
