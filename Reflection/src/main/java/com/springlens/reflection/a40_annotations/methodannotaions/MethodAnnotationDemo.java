package com.springlens.reflection.a40_annotations.methodannotaions;

import java.lang.reflect.Method;
import java.util.*;

public class MethodAnnotationDemo {
    public static void main (String[] args) throws Exception {
        Class<?> c = RouteController.class;
        Object controller = c.getDeclaredConstructor().newInstance();

        // 1. Build Registry
        Map<String, Method> registry = new HashMap<>();

        for (Method m : c.getDeclaredMethods()) {
            if (m.isAnnotationPresent(MyRoute.class)) {
                MyRoute r = m.getAnnotation(MyRoute.class);
                registry.put(r.path(), m);

                MyAuth auth = m.getAnnotation(MyAuth.class);
                Role[] roles = m.getAnnotationsByType(Role.class); // repeatable(Roles)

                System.out.print("route=" + r.path() + " | " + r.method());
                if (auth != null) System.out.print(" | auth=" + auth.value());
                if (roles.length > 0) {
                    System.out.print(" | roles=");
                    for (int i = 0; i < roles.length; i++) {
                        if (i > 0) System.out.print(",");
                        System.out.print(roles[i].value());
                    }
                }
                System.out.println(" | method=" + m.getName());
            }
        }

        // 2. Dispatching Requests
        dispatch(registry, controller, "/hello");
        dispatch(registry, controller, "/bye");
        dispatch(registry, controller, "/unknown");
    }

    private static void dispatch(Map<String, Method> registry, Object controller, String path) throws Exception {
        Method target = registry.get(path);
        if (target == null) {
            System.out.println("404 Not Found: " + path);
            return;
        }
        target.invoke(controller);
    }
}
