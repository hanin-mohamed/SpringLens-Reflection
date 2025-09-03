package com.springlens.reflection.a40_annotations.injection;

import com.springlens.reflection.a40_annotations.injection.controller.OrderController;
import com.springlens.reflection.a40_annotations.injection.repository.UserRepository;
import com.springlens.reflection.a40_annotations.injection.service.PaymentService;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class FieldInjectionDemo {
    public static void main(String[] args) throws Exception {
        Map<Class<?>, Object> registry = new HashMap<>();
        registry.put(UserRepository.class, new UserRepository());
        registry.put(PaymentService.class, new PaymentService());

        OrderController controller = new OrderController();
        injectFields(controller, registry);  // --> inject  paymentService and userRepo fields in order controller

        controller.placeOrder(7);
    }

    public static void injectFields(Object target, Map<Class<?>, Object> registry) {
        Class<?> cls = target.getClass();
        while (cls != null && cls != Object.class) {   // child → parent → ... → Object

            for (Field f : cls.getDeclaredFields()) {

                if (!f.isAnnotationPresent(Inject.class))
                    continue;

                Class<?> fieldType = f.getType();  // Autowiring by type
                Object bean = registry.get(fieldType);
                if (bean == null) {
                    System.out.println(" no bean for type: " + fieldType.getName());
                    continue;
                }

                try {
                    f.setAccessible(true);   //  for private fields
                    if (Modifier.isStatic(f.getModifiers())) {
                        f.set(null, bean);   // static → null as target object
                    } else {
                        f.set(target, bean);
                    }
                    System.out.println("injected: " + f.getName() + " <- " + fieldType.getSimpleName());
                } catch (IllegalAccessException e) {
                    System.out.println("cannot inject " + f.getName() + ": " + e);
                }
            }
            cls = cls.getSuperclass();  // to handle inherited fields (userRepo in BaseController)
        }
    }
}
