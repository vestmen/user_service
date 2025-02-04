package user_service.service.user.filter;

import user_service.dto.user.UserFilterDto;
import user_service.entity.User;

import java.util.stream.Stream;

public interface UserFilter {
    boolean isApplicable(UserFilterDto filter);

    Stream<User> apply(Stream<User> eventStream, UserFilterDto filter);
}
