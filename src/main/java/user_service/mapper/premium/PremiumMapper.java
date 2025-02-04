package user_service.mapper.premium;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import user_service.dto.premium.PremiumResponseDto;
import user_service.entity.premium.Premium;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PremiumMapper {
    @Mapping(source = "user.id", target = "userId")
    PremiumResponseDto toPremiumResponseDto(Premium premium);
}
