package ya.qwester345.events.service.api;

import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.EventType;
import ya.qwester345.events.dto.EventCreateDto;
import ya.qwester345.events.dto.PageOfEvents;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IEventService{
    Event add(EventCreateDto eventCreate);

    PageOfEvents getByType(EventType type, Integer page, Integer size);

    Event getByUuid(UUID uuid);

    void update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate, EventCreateDto eventCreateDto);
}
