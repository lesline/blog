package reactor.back;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangshaolin
 * @create 2018/9/4
 */
public class BackPressure {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackPressure.class);

    /*
1. Flux.range是一个快的Publisher；
2. 在每次request的时候打印request个数；
3. 通过重写BaseSubscriber的方法来自定义Subscriber；
4. hookOnSubscribe定义在订阅的时候执行的操作；
5. 订阅时首先向上游请求1个元素；
6. hookOnNext定义每次在收到一个元素的时候的操作；
7. sleep 1秒钟来模拟慢的Subscriber；
8. 打印收到的元素；
9. 每次处理完1个元素后再请求1个。

这6个元素是以每秒1个的速度被处理的。由此可见range方法生成的Flux采用的是缓存的回压策略，能够缓存下游暂时来不及处理的元素。

*/
    @Test
    public void testBackpressure() {
        Flux.range(1, 6)    // 1
                .doOnRequest(n -> System.out.println("Request " + n + " values..."))    // 2
                .subscribe(new BaseSubscriber<Integer>() {  // 3
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) { // 4
                        System.out.println("Subscribed and make a request...");
                        request(1); // 5
                    }

                    @Override
                    protected void hookOnNext(Integer value) {  // 6
                        try {
                            TimeUnit.SECONDS.sleep(1);  // 7
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Get value [" + value + "]");    // 8
                        request(1); // 9
                    }
                });


    }

    interface MyEventListener<T> {
        void onDataChunk(List<T> chunk);

        void processComplete();
    }


    /**
     * 测试不同的onBackpressureXxx方法的效果。
     */
    @Test
    public void testOnBackPressureXxx() throws InterruptedException {
        Flux.range(1, 6)
                .onBackpressureBuffer()     // BUFFER
//                .onBackpressureDrop()     // DROP
//                .onBackpressureLatest()   // LATEST
//                .onBackpressureError()    // ERROR
                .doOnRequest(n -> System.out.println("         ===  request: " + n + " ==="))
                .publishOn(Schedulers.newSingle("newSingle"), 1);
        Thread.sleep(50000);
    }

    @Test
    public void testPullBackpressure() {
        Flux.just(1, 2, 3, 4)
                .log()
                .subscribe(new Subscriber<Integer>() {
                    private Subscription s;
                    int onNextAmount;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        s.request(2);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
                        onNextAmount++;
                        if (onNextAmount % 2 == 0) {
                            s.request(2);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSampleBackpressure() throws InterruptedException {
        Flux.range(1, 1000)
                .log()
                .delayElements(Duration.ofMillis(200))
                .sample(Duration.ofMillis(1000))
                .subscribe(e -> {
                    LOGGER.info("subscribe:{}", e);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });
        Thread.sleep(100 * 1000);
    }

    @Test
    public void testBufferBackpressure() throws InterruptedException {
        Flux.range(1, 1000)
//                .log()
                .delayElements(Duration.ofMillis(200))
                .buffer(Duration.ofMillis(800))
                .subscribe(e -> {
                    LOGGER.info("subscribe:{}", e);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });
        Thread.sleep(100 * 1000);
    }

    @Test
    public void onBackpressureBuffer() throws InterruptedException {
        Flux.range(1, 1000)
                .onBackpressureBuffer()     // BUFFER
                .log()
                .subscribe(e -> {
                    LOGGER.info("subscribe:{}", e);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });
        Thread.sleep(100 * 1000);
    }

    @Test
    public void create() throws InterruptedException {
        Flux flux = Flux.create(sink -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("create " + i + " ---");
                sink.next(i);

            }
            sink.complete();
        }, FluxSink.OverflowStrategy.DROP)
                .doOnComplete(() -> System.out.println("completed!"))
                .doOnRequest(n -> System.out.println("Request " + n + " values..."));    // 2;

        flux.subscribe(e -> LOGGER.info("subscribe:{}", e));

    }

    @Test
    public void OverflowStrategy() throws InterruptedException {
        Flux flux = Flux.create(sink -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("create " + i + " ---");
                sink.next(i);

            }
            sink.complete();
        }, FluxSink.OverflowStrategy.BUFFER) //1 调整不同的策略（BUFFER/DROP/LATEST/ERROR/IGNORE）观察效果，create方法默认为BUFFER；
                .publishOn(Schedulers.newSingle("newSingle"), 1)   //2 使用publishOn让后续的操作符和订阅者运行在一个单独的名为newSingle的线程上，第二个参数1是预取个数
                .doOnComplete(() -> System.out.println("completed!")) // 结束时打印
                .doOnRequest(n -> System.out.println("Request " + n + " values..."));//3 打印出每次的请求

        flux.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) { // 4
                System.out.println("Subscribed and make a request...");
                request(1); // 5
            }

            @Override
            protected void hookOnNext(Integer value) {  // 6
                try {
                    TimeUnit.SECONDS.sleep(1);  // 7
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Get value [" + value + "]");    // 8
                request(1); // 9
            }
        });
        Thread.sleep(100 * 1000);
    }

 /*
1-调整不同的策略（BUFFER/DROP/LATEST/ERROR/IGNORE）观察效果，create方法默认为BUFFER；
2-打印出每次的请求；
3-使用publishOn让后续的操作符和订阅者运行在一个单独的名为newSingle的线程上，第二个参数1是预取个数，也就是.publishOn作为订阅者每次向上游request的个数，默认为256，所以一定程度上也起到了缓存的效果，为了测试，设置为1。

通常情况下，发布者于订阅者并不在同一个线程上，这里使用publishOn来模拟这种情况。
 */


}