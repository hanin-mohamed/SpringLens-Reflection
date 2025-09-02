package com.springlens.reflection.a20_fields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldListDetailsDemo {
    public static void main(String[] args) throws Exception {
        String className = (args.length > 0)
                ? args[0]
                : "com.springlens.reflection.samples.Bag";

        Class<?> c = Class.forName(className);
        System.out.println("Class = " + c.getName());
        System.out.println("Fields: ");

        for (Field f : c.getDeclaredFields()) { // getDeclaredFields() to include private fields
            int modifiers = f.getModifiers();
            String modsText = Modifier.toString(modifiers);

            System.out.println(
                    f.getName()
                            + " : " + f.getType().getSimpleName() // get field type in simple name double not java.lang.Double
                            + " | " + modsText
            );
        }
    }
}
