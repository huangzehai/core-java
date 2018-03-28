package com.hzh.corejava.reflection;

import com.hzh.corejava.model.Address;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 了解Spring使用反射来实现对象的创建以及依赖注入
 */
public class BeanFactory {
    private static Map<String, Object> beans = new HashMap<>();

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String className = "com.hzh.corejava.model.User";
        BeanFactory example = new BeanFactory();
        Class<?> clazz = example.forName(className);
        Method[] methods = clazz.getMethods();
        Object instance = clazz.newInstance();
        Address address = new Address();
        address.setCity("Shenzhen");
        for (Method method : methods) {
            if (method.getName().contains("setAddress")) {
                method.invoke(instance, address);
            }
        }
        beans.put(className, instance);
        System.out.println(beans);
    }

    private Class<?> forName(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (className == null || className.isEmpty()) {
            throw new NullPointerException("Class name should not be empty.");
        }
        return Class.forName("com.hzh.corejava.model.User");
    }
}
