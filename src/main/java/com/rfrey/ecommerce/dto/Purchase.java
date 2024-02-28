package com.rfrey.ecommerce.dto;

import com.rfrey.ecommerce.entity.Address;
import com.rfrey.ecommerce.entity.Customer;
import com.rfrey.ecommerce.entity.Order;
import com.rfrey.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
