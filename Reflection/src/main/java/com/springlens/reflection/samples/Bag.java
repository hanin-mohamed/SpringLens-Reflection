package com.springlens.reflection.samples;

import java.util.List;

public class Bag {
    public String name = "default";
    private int count = 1;
    public static int STATIC_COUNTER = 0;
    public final String CODE = "CONST";
    public static final String TYPE = "BAG";
    private List<String> items;

    public Bag() {}
    public Bag(String name, int count) {
        this.name = name;
        this.count = count;
    }
}
