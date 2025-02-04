package user_service.mapper.premium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import user_service.dto.premium.PremiumResponseDto;
import user_service.entity.User;
import user_service.entity.premium.Premium;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static user_service.util.premium.PremiumFabric.getPremium;
import static user_service.util.premium.PremiumFabric.getResponsePremiumDto;
import static user_service.util.premium.PremiumFabric.getUser;

class PremiumMapperTest {
    private static final long PREMIUM_ID = 1;
    private static final long USER_ID = 1;
    private static final LocalDateTime START_DATE = LocalDateTime.now();
    private static final LocalDateTime END_DATE = START_DATE.plusDays(31);

    private final PremiumMapper premiumMapper = Mappers.getMapper(PremiumMapper.class);

    @Test
    @DisplayName("Given dto and successful map")
    void testToDto() {
        User user = getUser(USER_ID);
        Premium premium = getPremium(PREMIUM_ID, user, START_DATE, END_DATE);
        PremiumResponseDto responsePremiumDto = getResponsePremiumDto(PREMIUM_ID, USER_ID, START_DATE, END_DATE);

        assertThat(premiumMapper.toPremiumResponseDto(premium)).isEqualTo(responsePremiumDto);
    }

}