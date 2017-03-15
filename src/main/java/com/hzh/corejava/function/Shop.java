package com.hzh.corejava.function;

import java.util.ArrayList;
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
        Apple apple = new Apple();
        apple.setColor("green");
        apple.setWeight(160.89);
        apples.add(apple);

//        List<Apple> greenApples = filterApples(apples, Shop::isGreenApple);
        List<Apple> greenApples = apples.stream().filter((Apple a) -> a.getWeight() > 150).collect(Collectors.toList());
        greenApples.forEach(System.out::println);
    }
}
