package com.springlens.reflection.a40_annotations.methodannotaions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // available at runtime
@Target(ElementType.METHOD)
public @interface MyRoute {
    String path();
    String method() default "GET";
}