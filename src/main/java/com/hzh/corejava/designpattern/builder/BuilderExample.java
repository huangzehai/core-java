package com.hzh.corejava.designpattern.builder;

public class BuilderExample {
    public static void main(String[] args) {
        Car car = Car.builder().setColor("RED").setWheels(6).build();
        System.out.println(car);
    }
}
