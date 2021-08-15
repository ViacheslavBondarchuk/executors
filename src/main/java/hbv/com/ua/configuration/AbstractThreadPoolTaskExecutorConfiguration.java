package hbv.com.ua.configuration;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 9:13 PM
 **/

@Getter
@Setter
public abstract class AbstractThreadPoolTaskExecutorConfiguration implements ThreadPoolTaskExecutorConfiguration {
    private int threads;
    private long timeout;
    private TimeUnit timeUnit;
}
