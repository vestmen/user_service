package user_service.service.mentorship_request;

import user_service.dto.mentorship_request.RequestFilterDto;
import user_service.entity.MentorshipRequest;

import java.util.stream.Stream;

public interface RequestFilter {
    boolean isApplicable(RequestFilterDto filter);
    Stream<MentorshipRequest> apply(Stream<MentorshipRequest> mentorshipRequestStream, RequestFilterDto filter);
}
