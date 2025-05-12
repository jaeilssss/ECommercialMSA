package com.ecommercial.shopping.adminservice.presentation;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class AdminController {

    @Transactional
    public void test() {
        System.out.println("ds");
    }

    @Transactional(readOnly = true)
    public void test2() {
        System.out.println("read only");
    }
}
