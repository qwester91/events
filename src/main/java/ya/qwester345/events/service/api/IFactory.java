package ya.qwester345.events.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.EventCreateDto;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IFactory {
    Event add(EventType type, EventCreateDto eventCreateDto);

    Page<Event> getByType(EventType type, Pageable pageable);

    Event getByUuid(EventType type, UUID uuid);

    void update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate, EventCreateDto eventCreateDto);

}
