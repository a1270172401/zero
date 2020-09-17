package org.io.hydoskyzero.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 * @author 念着倒才子傻
 */
public class ThreadPools {

    public static ExecutorService exec;

    /**
     * 线程池中核心线程数最大值
     */
    private static final int CORE_POOL_SIZE = 20;

    /**
     * 线程池中能有最多线程数
     */
    private static final int MAXIMUM_POOL_SIZE = 40;

    /**
     * 空闲线程存活的最大时间
     */
    private static final int KEEP_ALIVE_TIME = 20;

    /**
     * 线程存活的时间单位
     */
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    /**
     * 存放排队线程的队列
     */
    private static final LinkedBlockingDeque<Runnable> QUEUE = new LinkedBlockingDeque<>(1024);

    /**
     * 超过线程池和队列容量的线程处理方式，这里是抛异常
     */
    private static final ThreadPoolExecutor.AbortPolicy FAIL_PLAN = new ThreadPoolExecutor.AbortPolicy();

    static {
        exec = new ThreadPoolExecutor(CORE_POOL_SIZE,MAXIMUM_POOL_SIZE,KEEP_ALIVE_TIME,TIME_UNIT,QUEUE, FAIL_PLAN);
    }


}
