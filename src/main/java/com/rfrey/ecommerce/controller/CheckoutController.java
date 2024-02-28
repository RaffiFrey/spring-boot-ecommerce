package com.rfrey.ecommerce.controller;

import com.rfrey.ecommerce.dto.Purchase;
import com.rfrey.ecommerce.dto.PurchaseResponse;
import com.rfrey.ecommerce.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        return checkoutService.placeOrder(purchase);
    }
}
