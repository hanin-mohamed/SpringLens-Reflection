package com.springlens.reflection.a40_annotations.classanotaions;


import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyInfo {
    String author();
    int version() default 1;
}
