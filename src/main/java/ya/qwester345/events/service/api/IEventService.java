package ya.qwester345.events.service.api;

import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.EventCreateDto;
import ya.qwester345.events.dto.PageOfEvents;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IEventService <T>{
    T add(EventCreateDto eventCreate, EventType type);

    PageOfEvents getByType(EventType type, Integer page, Integer size);

    T getByUuid(UUID uuid);

    void update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate, EventCreateDto eventCreateDto);
}
