package com.springlens.reflection.a10_methods;

import java.lang.reflect.Method;

public class MethodListDemo {
    public static void main(String[] args) throws Exception {
        String className = (args.length > 0) ? args[0] : "java.lang.String";
        Class<?> c = Class.forName(className);

        System.out.println("Class = " + c.getName());

        System.out.println("Methods: ");

        for (Method m : c.getDeclaredMethods()) {   // getDeclaredMethods() --> include private methods
            System.out.println(m.getName());  // print method name
        }
    }
}
