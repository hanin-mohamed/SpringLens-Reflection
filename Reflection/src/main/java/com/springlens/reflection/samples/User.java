package com.springlens.reflection.samples;

public class User {

    public String name;
    public int age;

    public User() {
        this("Anon", 0);
    }
    public User(String name, int age) {
        this.name = name; this.age = age;
    }

    @Override public String toString() {
        return "User{name=" + name + ", age=" + age + "}";
    }
}
