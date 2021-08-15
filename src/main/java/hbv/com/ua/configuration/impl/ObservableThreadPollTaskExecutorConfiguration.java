package hbv.com.ua.configuration.impl;

import hbv.com.ua.configuration.AbstractThreadPoolTaskExecutorConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static hbv.com.ua.constants.ConfigurationConstants.*;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 9:12 PM
 **/

@Configuration
@ConfigurationProperties(prefix = OBSERVABLE_PREFIX, ignoreInvalidFields = true)
@PropertySource(TASK_EXECUTOR_CONFIGURATION_PROPERTIES)
@ConditionalOnProperty(value = OBSERVABLE_TASK_EXECUTOR_INSTANTIATE, havingValue = OBSERVABLE_TASK_EXECUTOR_CONDITIONAL_HAVING_VALUE)
public class ObservableThreadPollTaskExecutorConfiguration extends AbstractThreadPoolTaskExecutorConfiguration {
}
