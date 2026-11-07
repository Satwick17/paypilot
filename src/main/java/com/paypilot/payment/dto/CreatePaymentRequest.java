
package com.paypilot.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreatePaymentRequest(

        @NotNull(message = "Order ID is required")
        UUID orderId,

        @NotBlank(message = "Payment reference is required")
        @Size(max = 100, message = "Payment reference cannot exceed 100 characters")
        String paymentReference,

        @NotBlank(message = "Payment method is required")
        @Pattern(
                regexp = "(?i)UPI|CARD|NET_BANKING|WALLET",
                message = "Payment method must be UPI, CARD, NET_BANKING or WALLET"
        )
        String paymentMethod

) {}
