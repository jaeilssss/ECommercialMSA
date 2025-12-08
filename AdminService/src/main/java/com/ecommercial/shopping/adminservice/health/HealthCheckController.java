package com.ecommercial.shopping.adminservice.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("health")
public class HealthCheckController {
    @GetMapping("/v1")
    public String check() {
        return "ok";
    }
}
