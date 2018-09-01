package jdk8.stream;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author zhangshaolin
 * @create 2018/8/26
 */
public class FlatMap {
    public static void main(String[] args) {
        List<Integer> mapResult = Stream.of(1, 2)
                .map(number -> number + 1).collect(toList());
        System.out.println(mapResult);


        List<Integer> flatMapResult = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
//                .flatMap(List::stream).collect(toList());
                .flatMap(numbers -> numbers.stream()).collect(toList());
        System.out.println(flatMapResult);


        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);


        // flatMap
        words.stream()
                .flatMap((String line) -> Arrays.stream(line.split("")))
                .distinct()
                .forEach(System.out::println);


        System.out.println("---------------1------------------");


        // flatMap
        Arrays.asList("Hello", "World").stream()
                .map(line -> line+"-")
                .distinct()
                .forEach(System.out::println);

        System.out.println("---------------2------------------");

        // flatMap
        Arrays.asList("Hello", "World").stream()
                .map(line -> line.split(""))
                .flatMap(line -> Arrays.stream(line))
                .distinct()
                .forEach(System.out::println);


    }
}