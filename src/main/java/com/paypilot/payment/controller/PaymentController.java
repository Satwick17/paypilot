package com.paypilot.payment.controller;

import com.paypilot.payment.dto.CreatePaymentRequest;
import com.paypilot.payment.entity.Payment;
import com.paypilot.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Payment createPayment(
            @Valid @RequestBody CreatePaymentRequest request
    ) {
        return paymentService.createPayment(
                request.orderId(),
                request.paymentReference(),
                request.paymentMethod()
        );
    }
}