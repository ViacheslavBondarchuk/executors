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
 * time: 4:46 PM
 **/

@Configuration
@ConfigurationProperties(prefix = LISTENABLE_PREFIX, ignoreInvalidFields = true)
@PropertySource(TASK_EXECUTOR_CONFIGURATION_PROPERTIES)
@ConditionalOnProperty(havingValue = LISTENABLE_TASK_EXECUTOR_CONDITIONAL_HAVING_VALUE, value = LISTENABLE_TASK_EXECUTOR_INSTANTIATE)
public class ListenableThreadPoolTaskExecutorConfiguration extends AbstractThreadPoolTaskExecutorConfiguration {
}