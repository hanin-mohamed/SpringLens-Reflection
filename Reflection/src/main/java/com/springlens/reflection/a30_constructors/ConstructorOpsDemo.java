package com.springlens.reflection.a30_constructors;

import com.springlens.reflection.samples.User;
import com.springlens.reflection.samples.SecretBox;
import com.springlens.reflection.samples.Child;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ConstructorOpsDemo {
    public static void main(String[] args) throws Exception {


        // All User constructors
        Class<?> cu = User.class;
        System.out.println("User constructors: ");
        for (Constructor<?> constructor : cu.getDeclaredConstructors()) {  // including private
            System.out.println(signatureOf(constructor));
        }

        // Create User instance using default constructor

        Constructor<User> noArg = User.class.getDeclaredConstructor(); // no-arg constructor
        User u1 = noArg.newInstance();
        System.out.println("new User: " + u1);

        //  Create User instance using 2-arg constructor(String,int)

        Constructor<User> twoArgs = User.class.getDeclaredConstructor(String.class, int.class);
        User u2 = twoArgs.newInstance("Haneen", 22);
        System.out.println("new User: " + u2);


        // SecretBox: private no-arg + public(String)
        Class<?> cs = SecretBox.class;
        System.out.println("\nSecretBox constructors: ");
        for (Constructor<?> constructor : cs.getDeclaredConstructors()) {
            System.out.println(signatureOf(constructor));
        }

        // public(String)
        Constructor<SecretBox> sbPub = SecretBox.class.getDeclaredConstructor(String.class);
        SecretBox s1 = sbPub.newInstance("OPEN");
        System.out.println("Public Constructor " + s1);

        // private()
        Constructor<SecretBox> sbPrivate = SecretBox.class.getDeclaredConstructor();
        sbPrivate.setAccessible(true);         // allow access to private constructor
        SecretBox s2 = sbPrivate.newInstance();
        System.out.println("Private constructor:" + s2);


        // Child: default + 2-arg(String,int) + inherited from Parent
        Class<?> cc = Child.class;
        System.out.println("\nChild constructors: ");
        for (Constructor<?> constructor : cc.getDeclaredConstructors()) {
            System.out.println(signatureOf(constructor));
        }

        // Child()
        Child c1 = Child.class.getDeclaredConstructor().newInstance();
        System.out.println("Child(): " + c1);

        // Child(String,int)
        Child c2 = Child.class.getDeclaredConstructor(String.class, int.class)
                .newInstance("from-parent", 5);
        System.out.println("Child(\"from-parent\",5) => " + c2);

        // Common Error Cases:
        try {
            User.class.getDeclaredConstructor(String.class, String.class);            // NoSuchMethodException --> param types don't match

        } catch (NoSuchMethodException e) {
            System.out.println("\n[Expected] NoSuchMethodException: " + e.getMessage());
        }

        try {
            Constructor<SecretBox> sbPriv2 = SecretBox.class.getDeclaredConstructor();
            sbPriv2.newInstance();       // IllegalAccessException --> private constructor, need setAccessible(true)
        } catch (IllegalAccessException e) {
            System.out.println("[Expected] IllegalAccessException: need setAccessible(true)");
        }
    }

    private static String signatureOf(Constructor<?> k) {
        String mods = Modifier.toString(k.getModifiers());
        String params = Arrays.toString(k.getParameterTypes())
                .replace("class ", "")
                .replace("[", "(").replace("]", ")");
        return mods + " " + k.getDeclaringClass().getSimpleName() + params;
    }
}
