package reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangshaolin
 * @create 2018/5/28
 */
public class Scheduler {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        System.out.println("-------------------------------");
        Flux.create(sink -> {
            System.out.println("--1--" + Thread.currentThread().getName());
            sink.next("【-" + Thread.currentThread().getName() + "-】");
            sink.complete();
        })
//                .publishOn(Schedulers.elastic())
                .map(x -> {
                    System.out.println("--2--" + Thread.currentThread().getName());
                    return String.format("[%s]B %s", Thread.currentThread().getName(), x);
                })
                .toStream()
                .forEach(System.out::println);
    }


    private static String getStringSync() {
        System.out.println("getStringSync");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello, Reactor!";
    }

    @Test
    public void test() throws InterruptedException {
        Mono.fromCallable(() -> getStringSync())    // 1
                .subscribe(System.out::println);
        System.out.println("----------");
//        Thread.sleep(5000);
    }
}