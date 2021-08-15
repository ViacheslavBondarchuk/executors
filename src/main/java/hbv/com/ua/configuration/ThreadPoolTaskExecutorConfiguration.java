package hbv.com.ua.configuration;

import java.util.concurrent.TimeUnit;

/**
 * author: viacheslavbondarchuk
 * date: 8/14/21
 * time: 9:14 PM
 **/
public interface ThreadPoolTaskExecutorConfiguration {
    int getThreads();
    long getTimeout();
    TimeUnit getTimeUnit();

    void setThreads(int threads);
    void setTimeout(long timeout);
    void setTimeUnit(TimeUnit timeUnit);
}
