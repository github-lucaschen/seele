package com.skybeyondtech.common.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Getter
@Setter
@EnableAsync
@Configuration
@ConfigurationProperties("system.thread.pool")
public class ThreadPoolConfig {

    /**
     * 线程池核心线程大小
     */
    private Integer corePoolSize = 100;

    /**
     * 线程池最大线程数量
     */
    private Integer maxPoolSize = 500;

    /**
     * 线程池队列长度
     */
    private Integer queueCapacity = 1000;

    /**
     * 线程池前缀
     */
    private String threadNamePrefix = "common-pool-";

    /**
     * 线程等待时间
     */
    private Integer keepAliveSeconds = 60;

    /**
     * when a task is added to the thread pool through execute(Runnable) method:
     * <p>
     * 1. if thread-pool's thread number is less than <strong>corePoolSize</strong> at this time,
     * even if all threads in the thread pool are idle,
     * new threads will be created to handle the added tasks.
     * 2. if thread-pool's thread number is equals than <strong>corePoolSize</strong> at this time,
     * but the buffer queue <strong>workQueue</strong> is not full,
     * the task is put into the buffer queue.
     * 3. if thread-pool's thread number is equals than <strong>corePoolSize</strong> at this time,
     * and the buffer queue <strong>workQueue</strong> is full,
     * but thread-pool's thread number is less than <strong>maxPoolSize</strong>,
     * new threads will be created to handle the added tasks.
     * 4. if thread-pool's thread number is equals than <strong>corePoolSize</strong> at this time,
     * and the buffer queue <strong>workQueue</strong> is full,
     * and thread-pool's thread number is equals than <strong>maxPoolSize</strong>,
     * then handle this task through the strategy specified by <strong>maxPoolSize</strong>
     * <p>
     * That is to say:
     * The priority of processing tasks is:
     * 1. corePoolSize
     * 2. workQueue
     * 3. maxPoolSize
     * <p>
     * If all three are full, use <strong>maxPoolSize</strong> to handle rejected tasks
     *
     * @return Custom TaskExecutor
     */
    @Bean("commonTaskExecutor")
    public TaskExecutor commonTaskExecutor() {
        // 通过 Runtime 方法来获取当前服务器 CPU 内核数量，根据 CPU 内核数量来创建核心线程数和最大线程数
        int threadCount = Runtime.getRuntime().availableProcessors();
        final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(Math.min(threadCount, corePoolSize));
        threadPoolTaskExecutor.setMaxPoolSize(Math.min(threadCount, maxPoolSize));
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setThreadNamePrefix(threadNamePrefix);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
        return threadPoolTaskExecutor;
    }
}