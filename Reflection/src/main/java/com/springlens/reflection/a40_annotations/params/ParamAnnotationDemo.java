package com.springlens.reflection.a40_annotations.params;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class ParamAnnotationDemo {
    public static void main(String[] args) throws Exception {

        // Dummy request parameters data
        Map<String, String> request = new HashMap<>();
        request.put("id", "42");
        request.put("active", "true");

        Method method = UserController.class.getDeclaredMethod(
                "getUser", int.class, boolean.class
        );

        Object[] invokeArgs = new Object[method.getParameterCount()];
        Parameter[] params = method.getParameters();
        for (int i = 0; i < params.length; i++) {
            Parameter p = params[i];
            MyParam paramAnnotation = p.getAnnotation(MyParam.class);

            // if annotation present, get value from request map
            if (paramAnnotation != null) {
                String key = paramAnnotation.value();
                String raw = request.get(key);

                // convert String to required type
                if (raw != null) {
                    if (p.getType() == int.class) {
                        invokeArgs[i] = Integer.parseInt(raw);
                    } else if (p.getType() == boolean.class) {
                        invokeArgs[i] = Boolean.parseBoolean(raw);
                    } else {
                        invokeArgs[i] = raw; // default to String
                    }
                }
            }
        }

        // execute the method
        UserController controller = new UserController();
        method.invoke(controller, invokeArgs);
    }
}
