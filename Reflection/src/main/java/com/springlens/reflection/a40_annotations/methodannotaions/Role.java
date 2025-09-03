package com.springlens.reflection.a40_annotations.methodannotaions;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Roles.class)
public @interface Role {
    String value();
}