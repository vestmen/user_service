package user_service.service.premium;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import user_service.dto.payment.PaymentResponseDto;
import user_service.entity.User;
import user_service.entity.premium.Premium;
import user_service.entity.premium.PremiumPeriod;
import user_service.exception.payment.UnSuccessPaymentException;
import user_service.exception.premium.PremiumValidationFailureException;

import java.time.LocalDateTime;
import java.util.Optional;

import static user_service.entity.payment.PaymentStatus.FAILED;
import static user_service.service.premium.util.PremiumErrorMessages.UNSUCCESSFUL_PREMIUM_PAYMENT;
import static user_service.service.premium.util.PremiumErrorMessages.USER_ALREADY_HAS_PREMIUM;

@Slf4j
@Service
public class PremiumValidationService {

    public void validateUserForSubPeriod(long userId, User user) {
        log.info("Verification of User with id: {} for buying premium subscription", userId);
        getActivePremium(user).ifPresent(premium -> {
            throw new PremiumValidationFailureException(USER_ALREADY_HAS_PREMIUM, userId, premium.getEndDate());
        });
    }

    public void checkPaymentResponse(PaymentResponseDto paymentResponse, long userId, PremiumPeriod period) {
        log.info("Check payment response: {}", paymentResponse);
        if (paymentResponse.status() == FAILED) {
            throw new UnSuccessPaymentException(UNSUCCESSFUL_PREMIUM_PAYMENT, userId, period.getDays());
        }
    }

    private Optional<Premium> getActivePremium(User user) {
        return user.getPremiums()
                .stream()
                .filter(premium -> premium.getEndDate().isAfter(LocalDateTime.now()))
                .findFirst();
    }
}
