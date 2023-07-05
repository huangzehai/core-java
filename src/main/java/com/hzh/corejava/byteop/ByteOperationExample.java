package com.hzh.corejava.byteop;

public class ByteOperationExample {
    public static void main(String[] args) {
        System.out.println(add2(9, 233));

        int num = 8;
        System.out.println(num ^ num ^ num ^ num ^ num ^ num);
        System.out.println(5 / 2);
    }

    private static int add(int a, int b) {
        // 定理1：设a，b为两个二进制数，则a+b = a^b + (a&b)<<1。
        //进位
        int jw = a & b;
        //原位
        int yw = a ^ b;
        while (jw != 0) {
            //临时a：  a^b
            int ta = yw;
            //临时b： (a&b)<<1
            int tb = jw << 1;
            jw = ta & tb;
            yw = ta ^ tb;
        }
        return yw;
    }


    private static int add2(int a, int b) {
        // 设a，b为两个二进制数，则a+b = a^b + (a&b)<<1。
        //进位
        int jw = (a & b) << 1;
        //原位
        int yw = a ^ b;
        if (jw == 0) {
            return yw;
        } else {
            return add2(yw, jw);
        }
    }
}
