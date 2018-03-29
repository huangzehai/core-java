package com.hzh.corejava.designpattern.builder;

public class Car {
    public final int wheels;
    public final String color;

    private Car(final int wheels, final String color) {
        this.wheels = wheels;
        this.color = color;
    }

    public static Builder<Car> builder() {
        return new Builder<>(Car::new);
    }

    @Override
    public String toString() {
        return "Car [wheels = " + wheels + ", color = " + color + "]";
    }
}