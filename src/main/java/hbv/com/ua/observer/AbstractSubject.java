package hbv.com.ua.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * author: viacheslavbondarchuk
 * date: 8/15/21
 * time: 6:00 PM
 **/
public abstract class AbstractSubject<D> implements Subject<D> {
    private final List<Observer<D>> observers = new LinkedList<>();

    @Override
    public void subscribe(Observer<D> observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void unsubscribe(Observer<D> observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyAllSubscribers(D data) {
        observers.forEach(observer -> observer.update(data));
    }
}
