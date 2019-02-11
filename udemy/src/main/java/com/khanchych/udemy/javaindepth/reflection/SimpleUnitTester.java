package com.khanchych.udemy.javaindepth.reflection;

import java.lang.reflect.Method;

public class SimpleUnitTester {

    public int execute(Class clazz) throws Exception {
        int failedCount = 0;

        Object instance = clazz.newInstance();

        for (Method method: clazz.getDeclaredMethods()) {
            if (method.getName().startsWith("test") && method.getReturnType() == boolean.class) {
                if (!(Boolean) method.invoke(instance)) {
                    failedCount++;
                }
            }
        }

        return failedCount;
    }

    public static void main(String[] args) throws Exception {
        SimpleUnitTester unitTester = new SimpleUnitTester();

        System.out.println(unitTester.execute(Reflection.class));
    }

}