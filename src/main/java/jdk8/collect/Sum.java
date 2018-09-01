package jdk8.collect;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * @author zhangshaolin
 * @create 2018/8/27
 */
public class Sum {
    public static void main(String[] args) {
        List<Dish> menu =
                asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                        new Dish("beef", false, 700, Dish.Type.MEAT),
                        new Dish("chicken", false, 400, Dish.Type.MEAT),
                        new Dish("french fries", true, 530, Dish.Type.OTHER),
                        new Dish("rice", true, 350, Dish.Type.OTHER),
                        new Dish("season fruit", true, 120, Dish.Type.OTHER),
                        new Dish("pizza", true, 550, Dish.Type.OTHER),
                        new Dish("prawns", false, 400, Dish.Type.FISH),
                        new Dish("salmon", false, 450, Dish.Type.FISH));

        int sum1 = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        int sum2 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        int sum3 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        int sum4 = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        int sum5 = menu.stream().mapToInt(Dish::getCalories).sum();


        System.out.println(sum1);
        System.out.println(sum2);
        System.out.println(sum3);
        System.out.println(sum4);
        System.out.println(sum5);
    }
}