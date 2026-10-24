package com.paypilot.payment.service;

import com.paypilot.common.exception.DuplicatePaymentReferenceException;
import com.paypilot.common.exception.OrderNotFoundException;
import com.paypilot.order.entity.Order;
import com.paypilot.order.repository.OrderRepository;
import com.paypilot.payment.entity.Payment;
import com.paypilot.payment.entity.PaymentStatus;
import com.paypilot.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.paypilot.common.exception.InvalidPaymentTransitionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import com.paypilot.payment.entity.PaymentStatus;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentStateMachine paymentStateMachine;

    public PaymentService(
            PaymentRepository paymentRepository,
            OrderRepository orderRepository,
            PaymentStateMachine paymentStateMachine
    ) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.paymentStateMachine = paymentStateMachine;
    }

    @Transactional
    public Payment createPayment(
            UUID orderId,
            String paymentReference,
            String paymentMethod
    ) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(
                        "Order not found: " + orderId
                ));

        if (paymentRepository.existsByPaymentReference(paymentReference)) {
            throw new DuplicatePaymentReferenceException(
                    "Payment reference already exists"
            );
        }

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        Payment payment = new Payment(
                UUID.randomUUID(),
                order.getId(),
                paymentReference,
                order.getAmount(),
                order.getCurrency(),
                PaymentStatus.CREATED,
                paymentMethod.toUpperCase(),
                null,
                null,
                now,
                now
        );

        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment transitionPaymentStatus(
            UUID paymentId,
            PaymentStatus newStatus
    ) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new InvalidPaymentTransitionException(
                        "Payment not found: " + paymentId
                ));

        paymentStateMachine.validateTransition(
                payment.getStatus(),
                newStatus
        );

        payment.setStatus(newStatus);
        payment.setUpdatedAt(OffsetDateTime.now(ZoneOffset.UTC));

        if (newStatus != PaymentStatus.FAILED) {
            payment.setFailureReason(null);
        }

        return paymentRepository.save(payment);
    }
}