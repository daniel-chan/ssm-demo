package com.daselearn.ssmjar.strategy.org.cent.scanner.core.util;

import java.util.concurrent.*;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/11.
 * @description: 线程池工具类
 */
public enum ExecutorUtil {
    ;
    /**
     * 线程池
     */
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors()*2,
            Integer.MAX_VALUE,
            5,
            TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            new NamedThreadFactory("class-scanner-thread-"));


    /**
     * 在线程池执行线程
     *
     * @param thread
     */
    public static void executeInPool(Thread thread) {
        executor.execute(thread);
    }
}
