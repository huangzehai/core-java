package com.hzh.corejava.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

/**
 * 动态代理与被代理的接口和真实类没有绑定，仅在创建代理时才确定被代理的对象.
 */
public class AuthenticationProxy implements InvocationHandler {

    /**
     * 被代理的对象
     */
    private Object obj;

    /**
     * @param obj 被代理的对象
     * @return
     */
    public static Object newInstance(Object obj) {
        return Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new AuthenticationProxy(obj));
    }


    private AuthenticationProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("检查是否有权限");
        if (new Random().nextInt(10) % 2 == 0) {
            Object result = method.invoke(obj, args);
            System.out.println("Success");
            return result;
        }
        throw new UnsupportedOperationException();
    }
}
