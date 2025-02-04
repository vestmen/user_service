package user_service.dto.payment;

import lombok.Builder;
import user_service.entity.payment.Currency;
import user_service.entity.payment.PaymentStatus;

import java.math.BigDecimal;

@Builder
public record PaymentResponseDto(
        PaymentStatus status,
        int verificationCode,
        long paymentNumber,
        BigDecimal amount,
        Currency currency,
        String message
) {
}
