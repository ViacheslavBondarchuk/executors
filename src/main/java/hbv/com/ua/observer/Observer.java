package hbv.com.ua.observer;

/**
 * author: viacheslavbondarchuk
 * date: 8/15/21
 * time: 5:58 PM
 **/
@FunctionalInterface
public interface Observer<D> {
    void update(D data);
}
