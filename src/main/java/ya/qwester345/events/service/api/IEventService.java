package ya.qwester345.events.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.EventCreateDto;
import ya.qwester345.events.dto.ListOfEvents;
import ya.qwester345.events.dto.factory.EventDtoFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IEventService <T>{
    T add(EventDtoFactory eventCreate);

    ListOfEvents<Event> getByType(EventType type, Pageable pageable);

    T getByUuid(UUID uuid);

    T update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate, EventDtoFactory eventCreateDto);
}
