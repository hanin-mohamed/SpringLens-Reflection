package com.springlens.reflection.a40_annotations.params;

public class UserController {
    public void getUser(@MyParam("id") int id,
                        @MyParam("active") boolean active) {
        System.out.println("getUser: id=" + id + " active=" + active);
    }

    public void noParams() {
        System.out.println("noParams called");
    }
}
