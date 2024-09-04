package school.faang.user_service.dto.event;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import school.faang.user_service.entity.event.EventStatus;
import school.faang.user_service.entity.event.EventType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EventDto {
    private Long id;
    @NotEmpty(message = "Название события не может быть пустым.")
    private String title;
    @NotNull(message = "Дата начала события не может быть пустой.")
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @NotNull(message = "ID организатора не может быть пустым.")
    private Long ownerId;
    private String description;
    private List<Long> relatedSkillsIds;
    private String location;
    @NotEmpty(message = "Тип события не может быть пустым.")
    private String type;
    @NotEmpty(message = "Статус события не может быть пустым.")
    private String status;
    private int maxAttendees;
}
