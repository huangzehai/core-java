package com.hzh.corejava.array;

import java.util.Arrays;

public class ArrayExample {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        modifyArray(arr);
        System.out.println(Arrays.toString(arr));

        int i = 1;
        modifyInt(i);
        System.out.println(i);
    }

    private static void modifyArray(int[] arr) {
        arr[0] = -1;
    }

    private static void modifyInt(int i) {
        i = -1;
    }
}
