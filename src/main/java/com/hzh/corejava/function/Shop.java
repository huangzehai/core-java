package com.hzh.corejava.function;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by lenovo on 2017/3/15.
 */
public class Shop {

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("green", 1609));
        apples.add(new Apple("red", 23.9));
        apples.add(new Apple("red", 123));
        apples.add(new Apple("blue", 23.9));

//        List<Apple> greenApples = filterApples(apples, Shop::isGreenApple);
//        List<Apple> greenApples = apples.stream().filter((Apple a) -> a.getWeight() > 150).collect(Collectors.toList());
        apples.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));

//        apples.forEach(System.out::println);

        Predicate<Apple> redApples = (apple) -> apple.getColor().equalsIgnoreCase("red");
        List<Apple> notRedApples = filterApples(apples, redApples.negate().and((a) -> a.getWeight() > 100));
        notRedApples.forEach(System.out::println);
    }
}
