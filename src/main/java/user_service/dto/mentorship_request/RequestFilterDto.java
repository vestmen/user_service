package user_service.dto.mentorship_request;

import lombok.Data;
import user_service.entity.RequestStatus;

@Data
public class RequestFilterDto {
    private String description;
    private Long requesterId;
    private Long receiverId;
    private RequestStatus status;
}
