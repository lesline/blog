package jdk8.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangshaolin
 * @create 2018/10/22
 */
public class ThenApplyTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> f =  future.thenApplyAsync(i -> i * 10).thenApply(i -> i.toString());
        System.out.println(f.get()); //"1000"
    }
}