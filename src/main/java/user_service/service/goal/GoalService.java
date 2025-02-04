package user_service.service.goal;

import user_service.dto.goal.GoalFilterDto;
import user_service.entity.goal.Goal;

import java.util.List;

public interface GoalService {
    Goal createGoal(Goal goal, Long userId, Long parentGoalId, List<Long> skillIds);

    Goal updateGoal(Goal goal, List<Long> skillIds);

    void deleteGoal(Long goalId);

    List<Goal> findSubGoalsByParentGoalId(Long parentGoalId, GoalFilterDto filterDto);

    List<Goal> findGoalsByUserId(Long userId, GoalFilterDto filter);

    void deleteGoalAndUnlinkChildren(Goal goal);
}
