package ya.qwester345.events.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.EventCreateDto;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IEventService <T>{
    T add(EventCreateDto eventCreate);

    Page<Event> getByType(EventType type, Pageable pageable);

    T getByUuid(UUID uuid);

    void update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate, EventCreateDto eventCreateDto);
}
