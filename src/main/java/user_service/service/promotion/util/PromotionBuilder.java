package user_service.service.promotion.util;

import org.springframework.stereotype.Component;
import user_service.entity.User;
import user_service.entity.event.Event;
import user_service.entity.promotion.EventPromotion;
import user_service.entity.promotion.PromotionTariff;
import user_service.entity.promotion.UserPromotion;

import java.time.LocalDateTime;

@Component
public class PromotionBuilder {

    public UserPromotion buildUserPromotion(User user, PromotionTariff tariff) {
        return UserPromotion
                .builder()
                .promotionTariff(tariff)
                .cost(tariff.getCost())
                .currency(tariff.getCurrency())
                .coefficient(tariff.getCoefficient())
                .user(user)
                .numberOfViews(tariff.getNumberOfViews())
                .audienceReach(tariff.getAudienceReach())
                .creationDate(LocalDateTime.now())
                .build();
    }

    public EventPromotion buildEventPromotion(Event event, PromotionTariff tariff) {
        return EventPromotion
                .builder()
                .promotionTariff(tariff)
                .cost(tariff.getCost())
                .currency(tariff.getCurrency())
                .coefficient(tariff.getCoefficient())
                .event(event)
                .numberOfViews(tariff.getNumberOfViews())
                .audienceReach(tariff.getAudienceReach())
                .creationDate(LocalDateTime.now())
                .build();
    }
}
