package user_service.mapper.mentorship_request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import user_service.dto.mentorship_request.MentorshipRequestDto;
import user_service.entity.MentorshipRequest;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface MentorshipRequestMapper {
    @Mapping(source = "requester.id", target = "requesterId")
    @Mapping(source = "receiver.id", target = "receiverId")
    MentorshipRequestDto toDto(MentorshipRequest mentorshipRequest);
}
