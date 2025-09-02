package com.springlens.reflection.a10_methods;

import com.springlens.reflection.samples.Calculator;
import com.springlens.reflection.samples.Person;

import java.lang.reflect.Method;

public class MethodInvokeDemo {
    public static void main(String[] args) throws Exception {

        // 1. Instance method: Calculator.add(double,double)
        Calculator calc = new Calculator();
        Method add = Calculator.class.getDeclaredMethod("add", double.class, double.class);  // get method by name and param types
        Object sum = add.invoke(calc, 5.0, 3.0);
        System.out.println(sum);   // 8


        // 2. Static method: Calculator.hello(String)
        Method hello = Calculator.class.getDeclaredMethod("hello", String.class);
        hello.invoke(null, "Haneen");     // Hello Haneen  --> static method(no need to create instance) so invoke with null instance


        // 3. Instance method with mixed params: Person.greet(String,int)
        Person p = new Person();
        Method greet = Person.class.getDeclaredMethod("greet", String.class, int.class);
        Object msg = greet.invoke(p, "Haneen", 2);
        System.out.println(msg);   // Hello Haneen x2
    }
}
