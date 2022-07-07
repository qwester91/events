package ya.qwester345.events.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.EventFilm;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.EventCreateDto;
import ya.qwester345.events.service.api.IEventService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Qualifier("EventFilmService")
public class EventFilmService implements IEventService<EventFilm> {
    @Override
    public EventFilm add(EventCreateDto eventCreate) {
        return null;
    }

    @Override
    public Page<Event> getByType(EventType type, Pageable pagiable) {
        return null;
    }

    @Override
    public EventFilm getByUuid(UUID uuid) {
        return null;
    }

    @Override
    public void update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate, EventCreateDto eventCreateDto) {

    }
}
