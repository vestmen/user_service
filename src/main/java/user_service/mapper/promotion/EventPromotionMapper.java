package user_service.mapper.promotion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import user_service.dto.promotion.EventPromotionResponseDto;
import user_service.entity.promotion.EventPromotion;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventPromotionMapper {
    @Mapping(source = "event.id", target = "eventId")
    EventPromotionResponseDto toEventPromotionResponseDto(EventPromotion promotion);
}
