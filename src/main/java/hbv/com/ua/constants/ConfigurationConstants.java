package hbv.com.ua.constants;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 9:43 PM
 **/
public interface ConfigurationConstants {
    String LISTENABLE_PREFIX = "listenable";
    String LISTENABLE_TASK_EXECUTOR_INSTANTIATE = "listenable.task.executor.instantiate";
    String LISTENABLE_TASK_EXECUTOR_CONDITIONAL_HAVING_VALUE = "true";

    String OBSERVABLE_PREFIX = "observable";
    String OBSERVABLE_TASK_EXECUTOR_INSTANTIATE = "observable.task.executor.instantiate";
    String OBSERVABLE_TASK_EXECUTOR_CONDITIONAL_HAVING_VALUE = "true";

    String TASK_EXECUTOR_SHUTDOWN_LISTENER_PREFIX = "task.executor.shutdown.listener";
    String TASK_EXECUTOR_CONFIGURATION_PROPERTIES = "classpath:threadPoolTaskExecutor.properties";

}
