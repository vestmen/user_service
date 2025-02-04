package user_service.service.user.filter;

import org.springframework.stereotype.Component;
import user_service.dto.user.UserExtendedFilterDto;
import user_service.entity.User;
import user_service.service.user.UserFilter;

import java.util.function.Predicate;

@Component
public class UserAboutFilter implements UserFilter {
    @Override
    public boolean isApplicable(UserExtendedFilterDto filters) {
        return filters.getAboutPattern() != null;
    }

    @Override
    public Predicate<User> getPredicate(UserExtendedFilterDto filters) {
        return user -> user.getAboutMe() != null && user.getAboutMe().contains(filters.getAboutPattern());
    }
}
