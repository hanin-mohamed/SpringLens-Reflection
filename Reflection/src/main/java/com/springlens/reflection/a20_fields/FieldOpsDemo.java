package com.springlens.reflection.a20_fields;

import com.springlens.reflection.samples.Bag;
import com.springlens.reflection.samples.FancyBag;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldOpsDemo {
    public static void main(String[] args) throws Exception {
        Bag bag = new Bag();
        Class<?> c = bag.getClass();

        System.out.println("\nClass = " + c.getName());
        System.out.println("Declared Fields in Bag: ");
        for (Field f : c.getDeclaredFields()) {
            System.out.println(
                    f.getName() + " : " + f.getType().getSimpleName() +
                            " | " + Modifier.toString(f.getModifiers())
            );
        }

        // 1) Final field test
        Field code = c.getDeclaredField("CODE");
        System.out.println("\nFinal field CODE (before) = " + code.get(bag));
        try {
            code.setAccessible(true);
            code.set(bag, "CHANGED");
            System.out.println("Final field CODE (after) = " + code.get(bag));
        } catch (Exception e) {
            System.out.println(" Failed to change final field " + e);
        }

        // Final Static field test
        Field type = Bag.class.getDeclaredField("TYPE");
        type.setAccessible(true);
        try {
            type.set(null, "OTHER");  // Error: can't change static final field!!
            System.out.println("[TYPE] reflect read = " + type.get(null));
        } catch (IllegalAccessException e) {
            System.out.println(" can't set static final TYPE: " + e.getClass().getSimpleName());
        }
        System.out.println("direct read TYPE = " + Bag.TYPE);


        // 2) Inheritance: FancyBag extends  Bag
        FancyBag fb = new FancyBag();
        Class<?> cf = fb.getClass();

        System.out.println("\nClass = " + cf.getName());

        System.out.println("All Public Fields in FancyBag (including inherited): ");
        for (Field f : cf.getFields()) {  // includes inherited public fields
            System.out.println(f.getName() + " : " + f.getType().getSimpleName());
        }

        System.out.println("\nDeclared Fields in FancyBag Only : ");
        for (Field f : cf.getDeclaredFields()) {
            System.out.println(f.getName() + " : " + f.getType().getSimpleName());
        }

        Field name = cf.getField("name");
        System.out.println("\nFancyBag inherited field [name] = " + name.get(fb));
    }
}
