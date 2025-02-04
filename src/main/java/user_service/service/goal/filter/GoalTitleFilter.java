package user_service.service.goal.filter;

import org.springframework.stereotype.Component;
import user_service.dto.goal.GoalFilterDto;
import user_service.entity.goal.Goal;

import java.util.function.Predicate;

@Component
public class GoalTitleFilter implements GoalFilter {
    @Override
    public boolean isApplicable(GoalFilterDto filter) {
        return filter.getTitle() != null;
    }

    public Predicate<Goal> apply(GoalFilterDto filter) {
        return goal -> goal.getTitle().contains(filter.getTitle());
    }
}
