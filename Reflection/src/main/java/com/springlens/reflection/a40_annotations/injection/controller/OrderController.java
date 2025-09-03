package com.springlens.reflection.a40_annotations.injection.controller;

import com.springlens.reflection.a40_annotations.injection.Inject;
import com.springlens.reflection.a40_annotations.injection.repository.UserRepository;
import com.springlens.reflection.a40_annotations.injection.service.PaymentService;

public class OrderController extends BaseController {
    @Inject
    private PaymentService paymentService;

    @Inject
    static UserRepository STATIC_REPO;

    public void placeOrder(int userId) {
        System.out.println("user=" + userRepo.findById(userId)
                + " | " + paymentService.pay()
                + " | static? " + (STATIC_REPO != null));
    }
}