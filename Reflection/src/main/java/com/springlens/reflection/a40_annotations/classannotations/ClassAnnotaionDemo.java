package com.springlens.reflection.a40_annotations.classannotations;

public class ClassAnnotaionDemo {
    public static void main(String[] args){
        printInfo(AnnoClassBase.class,"AnnoClassBase");
        printInfo(AnnoClassChild.class,"AnnoClassChild");
    }

    private static void printInfo(Class<?> cls, String className) {
        System.out.println("Class: " + className);
        boolean present = cls.isAnnotationPresent(MyInfo.class);
        System.out.println("Is this class has MyInfo annotation ? " + present);

        MyInfo info = cls.getAnnotation(MyInfo.class);
        if (info != null) {
            System.out.println("author: " + info.author()+", version: " + info.version());
        }

        boolean declared = cls.getDeclaredAnnotation(MyInfo.class) != null;
        System.out.println("Is @MyInfo declared on this class? " + declared); // false (child class see the inherited annotation but not declared on it)
    }

}
