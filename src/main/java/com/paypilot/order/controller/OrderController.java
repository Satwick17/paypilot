package com.paypilot.order.controller;

import com.paypilot.order.dto.CreateOrderRequest;
import com.paypilot.order.entity.Order;
import com.paypilot.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(
            @Valid @RequestBody CreateOrderRequest request
            ){
        return orderService.createOrder(
                request.merchantId(),
                request.orderNumber(),
                request.amount(),
                request.currency(),
                request.description()
        );
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
}
