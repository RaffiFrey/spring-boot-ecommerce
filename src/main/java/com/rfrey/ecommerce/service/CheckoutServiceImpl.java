package com.rfrey.ecommerce.service;

import com.rfrey.ecommerce.dao.CustomerRepository;
import com.rfrey.ecommerce.dto.Purchase;
import com.rfrey.ecommerce.dto.PurchaseResponse;
import com.rfrey.ecommerce.entity.Customer;
import com.rfrey.ecommerce.entity.Order;
import com.rfrey.ecommerce.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {

        Order order = purchase.getOrder();
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::addOrderItem);
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());
        Customer customer = purchase.getCustomer();

        String email = customer.getEmail();
        Customer existingCustomer = customerRepository.findByEmail(email);
        if (existingCustomer != null) {
            customer = existingCustomer;
        }
        customer.addOrder(order);
        customerRepository.save(customer);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
