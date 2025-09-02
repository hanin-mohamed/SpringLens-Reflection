package com.springlens.reflection.samples;

public class Parent {
    protected final String base;
    public Parent(String base) { this.base = base; }
    @Override public String toString() { return "Parent{base=" + base + "}"; }
}
