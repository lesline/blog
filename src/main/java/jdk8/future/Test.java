package jdk8.future;

import java.util.concurrent.*;

/**
 * @author zhangshaolin
 * @create 2018/8/29
 */
public class Test {


    /**
     * java7实现
     */
    public static void java7test() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                //异步执行耗时的操作
                return dongSomeLongComputation();
            }
        });

        doSomeThingElse();//异步操作的同时执行其它操作
        try {
            future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private static void doSomeThingElse() {

    }

    private static Integer dongSomeLongComputation() {
        return null;
    }
}