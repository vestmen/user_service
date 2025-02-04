package user_service.service.user;

import user_service.dto.user.UserExtendedFilterDto;
import user_service.entity.User;

import java.util.function.Predicate;

public interface UserFilter {
    boolean isApplicable(UserExtendedFilterDto filters);

    Predicate<User> getPredicate(UserExtendedFilterDto filters);
}
