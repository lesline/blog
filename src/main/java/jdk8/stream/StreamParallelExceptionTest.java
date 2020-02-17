package jdk8.stream;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author zhangshaolin
 * @create 2018/7/15
 */
public class StreamParallelExceptionTest {

    /**
     * 判断异步会不会终止主线程
     *
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        Set a = new HashSet();
        Stream.iterate(1, n -> n + 1).limit(12).parallel().forEach(v -> {
            a.add(v);

            if (v == 91) {
                throw new RuntimeException("ddd");
            }
            System.out.println(v);
        });

        System.out.println("------------");
        System.out.println(a.size());

        Thread.sleep(1000 * 5);
    }

    @Test
    public void a() {
        int loop = (int) Math.ceil((double) 9999 / (double) 5000);
        System.out.println(loop);
    }

}