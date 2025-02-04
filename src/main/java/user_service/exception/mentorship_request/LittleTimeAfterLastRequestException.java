package user_service.exception.mentorship_request;

import static user_service.service.mentorship_request.error_messages.MentorshipRequestErrorMessages.ONCE_EVERY_THREE_MONTHS;

public class LittleTimeAfterLastRequestException extends MentorshipRequestException {
    public LittleTimeAfterLastRequestException() {
        super(ONCE_EVERY_THREE_MONTHS);
    }
}
