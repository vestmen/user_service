package user_service.service.user.filter;

import org.springframework.stereotype.Component;
import user_service.dto.user.UserExtendedFilterDto;
import user_service.entity.User;
import user_service.service.user.UserFilter;

import java.util.function.Predicate;

@Component
public class UserPhoneFilter implements UserFilter {
    @Override
    public boolean isApplicable(UserExtendedFilterDto filters) {
        return filters.getPhonePattern() != null;
    }

    @Override
    public Predicate<User> getPredicate(UserExtendedFilterDto filters) {
        return user -> user.getPhone() != null && user.getPhone().contains(filters.getPhonePattern());
    }
}
