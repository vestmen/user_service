package user_service.mapper.goal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import user_service.dto.goal.GoalInvitationDto;
import user_service.entity.goal.GoalInvitation;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GoalInvitationMapper {
    @Mapping(target = "goal", ignore = true)
    @Mapping(target = "inviter", ignore = true)
    @Mapping(target = "invited", ignore = true)
    GoalInvitation toEntity(GoalInvitationDto goalInvitationDto);

    @Mapping(source = "goal.id", target = "goalId")
    @Mapping(source = "inviter.id", target = "inviterId")
    @Mapping(source = "invited.id", target = "invitedUserId")
    GoalInvitationDto toDto(GoalInvitation goalInvitation);
}
