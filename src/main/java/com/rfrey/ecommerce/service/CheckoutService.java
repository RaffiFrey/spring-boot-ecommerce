package com.rfrey.ecommerce.service;

import com.rfrey.ecommerce.dto.Purchase;
import com.rfrey.ecommerce.dto.PurchaseResponse;
import org.springframework.stereotype.Service;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
