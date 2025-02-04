package user_service.service.event.filters;

import org.springframework.stereotype.Component;
import user_service.dto.event.EventFilters;
import user_service.entity.event.Event;

import java.util.stream.Stream;

@Component
public class EventStartDateFilter implements EventFilter {
    @Override
    public boolean isApplicable(EventFilters eventFilters) {
        return eventFilters.getStartDate() != null;
    }

    @Override
    public Stream<Event> apply(Stream<Event> eventStream, EventFilters eventFilters) {
        return eventStream.filter(event -> event.getStartDate().isAfter(eventFilters.getStartDate()) ||
                event.getStartDate().isEqual(eventFilters.getStartDate()));
    }
}
