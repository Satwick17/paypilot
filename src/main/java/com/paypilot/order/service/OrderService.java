package com.paypilot.order.service;

import com.paypilot.merchant.repository.MerchantRepository;
import com.paypilot.order.entity.Order;
import com.paypilot.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import com.paypilot.common.exception.DuplicateOrderException;
import com.paypilot.common.exception.MerchantNotFoundException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MerchantRepository merchantRepository;

    public OrderService(
            OrderRepository orderRepository,
            MerchantRepository merchantRepository
    ) {
        this.orderRepository = orderRepository;
        this.merchantRepository = merchantRepository;
    }

    public Order createOrder(
            UUID merchantId,
            String orderNumber,
            BigDecimal amount,
            String currency,
            String description
    ) {

        if (!merchantRepository.existsById(merchantId)) {
            throw new MerchantNotFoundException("Merchant not found");
        }

        if (orderRepository.existsByOrderNumber(orderNumber)) {
            throw new DuplicateOrderException("Order number already exists");
        }

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        Order order = new Order(
                UUID.randomUUID(),
                merchantId,
                orderNumber,
                amount,
                currency.toUpperCase(),
                "CREATED",
                description,
                now,
                now
        );

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}