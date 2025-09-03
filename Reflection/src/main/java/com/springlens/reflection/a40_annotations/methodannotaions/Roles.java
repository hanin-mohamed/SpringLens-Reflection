package com.springlens.reflection.a40_annotations.methodannotaions;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Roles {
    Role[] value();
}