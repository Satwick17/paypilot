package com.paypilot.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreatePaymentRequest(
        @NotNull
        UUID orderId,

        @NotBlank
        @Size(max = 100)
        String paymentReference,

        @NotBlank
        @Size(max = 30)
        String paymentMethod
) {

}
