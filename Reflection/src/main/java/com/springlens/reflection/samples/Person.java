package com.springlens.reflection.samples;

public class Person {
    public String greet(String name, int times) {
        return "Hello " + name + " x" + times;
    }

    private void hidden() {
        System.out.println("private method .");
    }
}
