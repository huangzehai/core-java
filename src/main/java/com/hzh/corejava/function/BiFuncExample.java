package com.hzh.corejava.function;

public class BiFuncExample {
    public static void main(String[] args) {
        BiFunc<String, Integer, User> biFunc = User::new;
        User user = biFunc.apply("Tom", 33);
        System.out.println(user);
    }
}

class User {
    private String username;
    private int age;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
