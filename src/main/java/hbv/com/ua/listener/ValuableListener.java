package hbv.com.ua.listener;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 5:15 PM
 **/
@FunctionalInterface
public interface ValuableListener<T> {
    void execute(T t);
}
