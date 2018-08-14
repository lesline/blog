package rx;

import io.reactivex.Observable;

/**
 * @author zhangshaolin
 * @create 2018/7/23
 */
public class CacheTest {
    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> work = Observable.fromCallable(() -> {
            System.out.println("Doing some work");
            return 10;
        }).cache();
        work.subscribe(System.out::println);
        work.map(i -> i * 2).subscribe(System.out::println);


        Thread.sleep(1000);
    }
}