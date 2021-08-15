package hbv.com.ua.runner;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 7:21 PM
 **/
public interface Runner {
    Runner submit(Runnable task);
}