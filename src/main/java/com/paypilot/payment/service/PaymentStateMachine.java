package com.paypilot.payment.service;

import com.paypilot.payment.entity.PaymentStatus;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import com.paypilot.common.exception.InvalidPaymentTransitionException;

@Component
public class PaymentStateMachine {

    private static final Map<PaymentStatus, Set<PaymentStatus>> ALLOWED_TRANSITIONS =
            Map.of(
                    PaymentStatus.CREATED,
                    Set.of(PaymentStatus.INITIATED),

                    PaymentStatus.INITIATED,
                    Set.of(
                            PaymentStatus.PROCESSING,
                            PaymentStatus.FAILED
                    ),

                    PaymentStatus.PROCESSING,
                    Set.of(
                            PaymentStatus.SUCCESS,
                            PaymentStatus.FAILED
                    ),

                    PaymentStatus.SUCCESS,
                    Set.of(),

                    PaymentStatus.FAILED,
                    Set.of()
            );

    public boolean canTransition(
            PaymentStatus currentStatus,
            PaymentStatus newStatus
    ) {
        return ALLOWED_TRANSITIONS
                .getOrDefault(currentStatus, Set.of())
                .contains(newStatus);
    }

    public void validateTransition(
            PaymentStatus currentStatus,
            PaymentStatus newStatus
    ) {
        if (!canTransition(currentStatus, newStatus)) {
            throw new InvalidPaymentTransitionException(
                    "Invalid payment status transition: "
                            + currentStatus + " -> " + newStatus
            );
        }
    }
}