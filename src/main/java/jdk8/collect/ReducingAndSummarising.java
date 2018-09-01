package jdk8.collect;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static jdk8.collect.Dish.menu;


public class ReducingAndSummarising {

  public static long howManyDishes() {
    return menu.stream().collect(Collectors.counting());
  }

  public static long howManyDishesDirect() {
    return menu.stream().count();
  }

  public static Dish findMostCalorificDishViaMaxBy() {
    Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
    return menu.stream().collect(Collectors.maxBy(dishCaloriesComparator)).get();
  }

  // Generalised version of max, using reducing
  public static Dish findMostCalorificDishViaReducing(List<Dish> menu) {
    return menu.stream().collect(
        Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)
    ).get();
  }

  public static Dish findMostCalorificViaReducingExpanded() {
    BinaryOperator<Dish> moreCalorificOf = ((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
    return menu.stream().collect(Collectors.reducing(moreCalorificOf)).get();
  }

  public static int calculateTotalCalories() {
    return menu.stream().collect(Collectors.summingInt(Dish::getCalories));
  }

  public static int calculateTotalCalories2() {
    return menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
  }

  public static int calculateTotalCalories3() {
    return menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
  }

  public static int calculateTotalCalories4() {
    return menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
  }

  public static int calculateTotalCalories5() {
    return menu.stream().mapToInt(Dish::getCalories).sum();
  }

  public static Double calculateAverageCalories() {
    return menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
  }

  public static IntSummaryStatistics calculatemenuStatistics() {
    return menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
  }

  public static String getShortmenuGarbled() {
    return menu.stream().map(Dish::getName).collect(Collectors.joining());
  }

  // Quiz 6.1, answer 1
  public static String getShortmenuGarbledViaReducing1() {
    return menu.stream().map(Dish::getName).collect(Collectors.reducing((s1, s2) -> s1 + s2)).get();
  }

  // Quiz 6.1, answer 2
  // public static String getShortmenuGarbledViaReducing2() {
  // This doesn't compile, reducing expects a BinaryOperator<T> which extends BiFunction<T, T, T>
  // i.e. lambda has to return same type as the operands (compare to answer 1 above)
  // return menu.stream().collect(Collectors.reducing((d1, d2) -> d1.getName() + d2.getName())).get();
  // }

  // Quiz 6.1, answer 3
  // This answer uses a generalization of {@link #reducing(Object, BinaryOperator)} which allows a transformation
  // of the elements before reduction.
  public static String getShortmenuGarbledViaReducing3() {
    return menu.stream().collect(Collectors.reducing("", Dish::getName, (s1, s2) -> s1 + s2));
  }

  public static String getShortmenu() {
    return menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
  }
}