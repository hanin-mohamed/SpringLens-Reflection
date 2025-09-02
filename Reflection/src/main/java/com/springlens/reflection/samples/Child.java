package com.springlens.reflection.samples;

public class Child extends Parent{
    public final int level;
    public Child() { super("default"); this.level = 1; }
    public Child(String base, int level) { super(base); this.level = level; }
    @Override public String toString() { return "Child{base=" + base + ", level=" + level + "}"; }

}
