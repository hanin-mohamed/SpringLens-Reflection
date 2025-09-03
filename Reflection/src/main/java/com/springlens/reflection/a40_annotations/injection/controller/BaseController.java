package com.springlens.reflection.a40_annotations.injection.controller;

import com.springlens.reflection.a40_annotations.injection.Inject;
import com.springlens.reflection.a40_annotations.injection.repository.UserRepository;


class BaseController {
    @Inject
    protected UserRepository userRepo;  // inherited field
}