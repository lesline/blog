package jdk8.function;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;

/**
 * @author zhangshaolin
 * @create 2018/9/2
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 4, 6);
        Integer max = list.get(0);
        for (Integer value : list.subList(1, list.size())) {
            if (value > max) {
                max = value;
            }
        }
        int max1 = list.stream().reduce(0, Integer::max);
        Optional<Integer> max2 = list.stream().max(comparing(Integer::intValue));

    }
}