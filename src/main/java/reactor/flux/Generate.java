package reactor.flux;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author zhangshaolin
 * @create 2018/9/2
 */
public class Generate {
    public static void main(String[] args) {

        //通过generate生成元素
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);

        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);

        //通过create生成元素
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }
}