package hbv.com.ua.listener;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 6:21 PM
 **/
public interface ShutdownTaskExecutorListener extends Runnable {
    void shutdown();

    @Override
    default void run() {
        this.shutdown();
    }
}
