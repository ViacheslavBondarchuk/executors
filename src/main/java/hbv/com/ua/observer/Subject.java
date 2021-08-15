package hbv.com.ua.observer;

/**
 * author: viacheslavbondarchuk
 * date: 8/15/21
 * time: 5:57 PM
 **/
public interface Subject<D> {
    void subscribe(Observer<D> observer);

    void unsubscribe(Observer<D> observer);

    void notifyAllSubscribers(D data);
}
