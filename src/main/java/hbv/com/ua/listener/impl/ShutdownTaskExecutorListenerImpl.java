package hbv.com.ua.listener.impl;

import hbv.com.ua.configuration.impl.ShutdownTaskExecutorListenerConfiguration;
import lombok.AllArgsConstructor;

import java.util.concurrent.ExecutorService;


/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 6:28 PM
 **/

@AllArgsConstructor
public class ShutdownTaskExecutorListenerImpl implements hbv.com.ua.listener.ShutdownTaskExecutorListener {
    private final ShutdownTaskExecutorListenerConfiguration shutdownTaskExecutorListenerConfiguration;
    private final ExecutorService executorService;

    @Override
    public void shutdown() {
        try {
            if (!executorService.awaitTermination(shutdownTaskExecutorListenerConfiguration.getAwaitTermination(), shutdownTaskExecutorListenerConfiguration.getTimeUnit())) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
