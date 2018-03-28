package com.hzh.corejava.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AiSpeakerProxy implements InvocationHandler {

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
                new AiSpeakerProxy(obj));
    }


    private AiSpeakerProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(obj, args);
        System.out.println("after");
        return result;
    }
}
