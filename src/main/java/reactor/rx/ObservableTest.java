package reactor.rx;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * @author zhangshaolin
 * @create 2018/7/23
 */

public class ObservableTest {
    public static void main(String[] args) throws InterruptedException {
        Observable
                .fromCallable(() -> {
                   System.out.println("Reading on thread: " + currentThread().getName());
                    return readFile("input.txt");
                })
                .observeOn(Schedulers.computation()) // <-- setting scheduler
                .map(text -> {
                   System.out.println("Map on thread: " + currentThread().getName());
                    return text.length();
                })
                .subscribeOn(Schedulers.io()) // <-- setting scheduler
                .subscribe(value -> {
                   System.out.println("Result on thread: " + currentThread().getName());
                });
        sleep(1000);
    }

    private static String readFile(String s) {
        return "dddddddddddddddd";
    }
}