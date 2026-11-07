package com.paypilot.payment.dto;

import com.paypilot.payment.entity.PaymentStatus;
import jakarta.validation.constraints.NotNull;

public record UpdatePaymentRequest(
        @NotNull PaymentStatus status
) {
}