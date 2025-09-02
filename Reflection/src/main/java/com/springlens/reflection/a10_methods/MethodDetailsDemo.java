package com.springlens.reflection.a10_methods;

import java.lang.reflect.Method;

public class MethodDetailsDemo {
    public static void main(String[] args) throws Exception {
        String className = (args.length > 0)
                ? args[0]
                : "com.springlens.reflection.samples.Person";   // default class if no args

        Class<?> c = Class.forName(className);

        System.out.println("Class = " + c.getName());
        System.out.println("Method Details: ");

        for (Method m : c.getDeclaredMethods()) {
            System.out.print("name=" + m.getName());
            System.out.print(" | returns=" + m.getReturnType().getSimpleName());
            System.out.print(" | params=(");
            Class<?>[] ps = m.getParameterTypes();
            for (int i = 0; i < ps.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(ps[i].getSimpleName()); // print parameter type name in simple form
            }
            System.out.println(")");
        }
    }
}
