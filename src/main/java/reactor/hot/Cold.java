package reactor.hot;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.util.Arrays;

/**
 * @author zhangshaolin
 * @create 2018/9/3
 */
public class Cold {
    @Test
    public void testCodeSequence() {
        Flux<String> source = Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                .map(String::toUpperCase);

        source.subscribe(d -> System.out.println("Subscriber 1: " + d));
        System.out.println();
        source.subscribe(d -> System.out.println("Subscriber 2: " + d));
    }

    @Test
    public void testHotSequence() {
        UnicastProcessor<String> hotSource = UnicastProcessor.create();

        Flux<String> hotFlux = hotSource.publish()
                .autoConnect()
                .map(String::toUpperCase);

        hotFlux.subscribe(d -> System.out.println("Subscriber 1 to Hot Source: " + d));

        hotSource.onNext("blue");
        hotSource.onNext("green");

        hotFlux.subscribe(d -> System.out.println("Subscriber 2 to Hot Source: " + d));

        hotSource.onNext("orange");
        hotSource.onNext("purple");
        hotSource.onComplete();
    }
}