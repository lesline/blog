package io.bio.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by 陈钊 on 2017/1/3.
 *
 * @description
 * 线程池的辅助类，将线程提交到ExecutorService进行处理
 */
public class TimeServerHandleExecutePool {

    private ExecutorService executorService;
    public TimeServerHandleExecutePool(int maxPoolSize,int queueSize) {
        this.executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSize,120L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queueSize));
    }
    public  void execute(Runnable runnable){
        executorService.execute(runnable);
    }
}
