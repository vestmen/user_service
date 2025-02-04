package user_service.mapper.promotion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import user_service.dto.promotion.UserPromotionResponseDto;
import user_service.entity.promotion.UserPromotion;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserPromotionMapper {
    @Mapping(source = "user.id", target = "userId")
    UserPromotionResponseDto toUserPromotionResponseDto(UserPromotion promotion);
}
