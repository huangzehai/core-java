package com.hzh.corejava.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicReferenceFieldUpdaterExample {

    public static void main(String[] args) {
        AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(User.class, String.class, "username");
        User user = new User("Kate");
        String username;
        do {
            username = user.username;
        } while (!updater.compareAndSet(user, username, "Tom"));

        System.out.println(user.username);
    }
}

class User {
    volatile String username;

    public User(String username) {
        this.username = username;
    }
}
