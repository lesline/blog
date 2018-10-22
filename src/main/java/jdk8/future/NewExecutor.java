package jdk8.future;

import java.util.concurrent.*;

/**
 * @author zhangshaolin
 * @create 2018/10/17
 */
public class NewExecutor {
    public final static Executor executor = Executors.newFixedThreadPool(100, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);//使用守护线程——这种方式不会阻止程序的关停
            return t;
        }
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            //长时间的计算任务
            return "1·00";
        }, executor);
        System.out.println(future.get());
        Thread.sleep(2000);
    }

    public static <T> T getResult(Future<T> future) {
        T result = null;
        try {
            result = future.get(1000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}