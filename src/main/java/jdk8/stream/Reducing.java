package jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reducing {

    public static void main(String... args) {

        //求和
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        int sum1 = numbers.stream().reduce(0,  (a, b) -> Integer.sum(a, b));
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        int sum3 = numbers.stream().mapToInt(Integer::intValue).sum();

        System.out.println(sum + " " + sum2);


        //最大值
        int max = numbers.stream().reduce(0, Integer::max);
        System.out.println(max);

        //最小值
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

    }
}
