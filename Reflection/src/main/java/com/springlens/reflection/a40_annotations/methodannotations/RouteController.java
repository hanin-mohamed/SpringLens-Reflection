package com.springlens.reflection.a40_annotations.methodannotations;

public class RouteController {

    @MyRoute(path = "/hello")
    @MyAuth("token")
    @Role(("USER"))
    @Role("ADMIN")
    public void sayHello() {
        System.out.println("Hello World!");
    }

    @MyRoute(path = "/bye", method = "POST")
    public void sayBye() {
        System.out.println("Bye!");
    }

    public void noRoute() {
        System.out.println("Normal method without route");
    }

}
