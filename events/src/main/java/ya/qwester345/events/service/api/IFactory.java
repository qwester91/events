package ya.qwester345.events.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.EventCreateDto;
import ya.qwester345.events.dto.ListOfEvents;
import ya.qwester345.events.dto.ReadDto;
import ya.qwester345.events.dto.factory.EventDtoFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IFactory {
    ReadDto add(EventType type, EventDtoFactory eventCreateDto);

    ListOfEvents<Event> getByType(EventType type, Pageable pageable);

    ReadDto getByUuid(EventType type, UUID uuid);

    void update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate, EventDtoFactory eventCreateDto);

}
