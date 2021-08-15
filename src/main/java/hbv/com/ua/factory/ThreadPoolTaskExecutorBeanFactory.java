package hbv.com.ua.factory;

import hbv.com.ua.configuration.impl.ShutdownTaskExecutorListenerConfiguration;
import hbv.com.ua.configuration.impl.ListenableThreadPoolTaskExecutorConfiguration;
import hbv.com.ua.configuration.impl.ObservableThreadPollTaskExecutorConfiguration;
import hbv.com.ua.executor.ListenableThreadPoolTaskExecutor;
import hbv.com.ua.executor.ObservableThreadPoolTaskExecutor;
import hbv.com.ua.executor.impl.ListenableThreadPoolTaskExecutorImpl;
import hbv.com.ua.executor.impl.ObservableThreadPoolTaskExecutorImpl;
import hbv.com.ua.listener.impl.ShutdownTaskExecutorListenerImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static hbv.com.ua.constants.ConfigurationConstants.*;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 6:32 PM
 **/
@Configuration
@AllArgsConstructor
public class ThreadPoolTaskExecutorBeanFactory {
    private final ShutdownTaskExecutorListenerConfiguration shutdownTaskExecutorListenerConfiguration;

    @Bean("listenableThreadPoolTaskExecutor")
    @ConditionalOnProperty(havingValue = LISTENABLE_TASK_EXECUTOR_CONDITIONAL_HAVING_VALUE, value = LISTENABLE_TASK_EXECUTOR_INSTANTIATE)
    public ListenableThreadPoolTaskExecutor listenableThreadPoolExecutor(ListenableThreadPoolTaskExecutorConfiguration listenableThreadPoolTaskExecutorConfiguration) {
        ListenableThreadPoolTaskExecutor fixedThreadPoolListenableExecutor = new ListenableThreadPoolTaskExecutorImpl(listenableThreadPoolTaskExecutorConfiguration);
        fixedThreadPoolListenableExecutor.registerShutdownListener(
                new ShutdownTaskExecutorListenerImpl(shutdownTaskExecutorListenerConfiguration, fixedThreadPoolListenableExecutor.getExecutorService()));
        return fixedThreadPoolListenableExecutor;
    }

    @Bean("observableThreadPoolTaskExecutor")
    @ConditionalOnProperty(havingValue = OBSERVABLE_TASK_EXECUTOR_CONDITIONAL_HAVING_VALUE, value = OBSERVABLE_TASK_EXECUTOR_INSTANTIATE)
    public ObservableThreadPoolTaskExecutor observableThreadPoolTaskExecutor(ObservableThreadPollTaskExecutorConfiguration observableThreadPollTaskExecutorConfiguration) {
        ObservableThreadPoolTaskExecutor observableThreadPoolTaskExecutor = new ObservableThreadPoolTaskExecutorImpl(observableThreadPollTaskExecutorConfiguration);
        observableThreadPoolTaskExecutor.registerShutdownListener(
                new ShutdownTaskExecutorListenerImpl(shutdownTaskExecutorListenerConfiguration, observableThreadPoolTaskExecutor.getExecutorService()));
        return observableThreadPoolTaskExecutor;
    }
}
