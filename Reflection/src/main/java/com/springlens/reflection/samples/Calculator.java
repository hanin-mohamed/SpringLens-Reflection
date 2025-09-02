package com.springlens.reflection.samples;

public class Calculator {
    public double add(double a, double b) { return a + b; }
    public double pow(double base, double exp) { return Math.pow(base, exp); }

    public static void hello(String name) {
        System.out.println("Hello " + name);
    }
}
