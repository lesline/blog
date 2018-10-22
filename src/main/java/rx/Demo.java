package rx;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import org.junit.Test;

/**
 * @author zhangshaolin
 * @create 2018/9/2
 */
public class Demo {

    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);
    }

    @Test
    public void rxjava2() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello world");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    @Test
    public void rxjava21() {
        Observable.just("Hello World").subscribe(System.out::println);
    }

    public static void rxjava1() {
        /*
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello world");
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Complete");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("Get:" + s);
            }
        });
        */
    }
}