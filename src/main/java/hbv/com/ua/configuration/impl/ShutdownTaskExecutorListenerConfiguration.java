package hbv.com.ua.configuration.impl;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.TimeUnit;

import static hbv.com.ua.constants.ConfigurationConstants.TASK_EXECUTOR_CONFIGURATION_PROPERTIES;
import static hbv.com.ua.constants.ConfigurationConstants.TASK_EXECUTOR_SHUTDOWN_LISTENER_PREFIX;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 6:22 PM
 **/
@Data
@Configuration
@PropertySource(TASK_EXECUTOR_CONFIGURATION_PROPERTIES)
@ConfigurationProperties(prefix = TASK_EXECUTOR_SHUTDOWN_LISTENER_PREFIX, ignoreInvalidFields = true)
public class ShutdownTaskExecutorListenerConfiguration {
    private long awaitTermination;
    private TimeUnit timeUnit;
}
