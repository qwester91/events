package ya.qwester345.events.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ya.qwester345.events.dao.api.IEventConcertDao;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.EventConcert;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.ConcertCreateDto;
import ya.qwester345.events.dto.EventCreateDto;
import ya.qwester345.events.dto.factory.EventDtoFactory;
import ya.qwester345.events.service.api.IEventService;
import ya.qwester345.events.service.utils.ConcertMapper;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Qualifier("EventConcertService")
public class EventConcertService implements IEventService <EventConcert> {
    private final IEventConcertDao dao;

    public EventConcertService(IEventConcertDao dao) {
        this.dao = dao;
    }

    @Override
    public EventConcert add(EventDtoFactory eventCreate) {
        EventConcert event = new ConcertMapper().concertFromDto(eventCreate);
        return event;
    }

    @Override
    public Page<Event> getByType(EventType type, Pageable pageable) {
        return dao.findAllByType(type, pageable);
    }

    @Override
    public EventConcert getByUuid(UUID uuid) {
        return dao.findById(uuid).orElseThrow();

    }

    @Override
    public void update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate, EventDtoFactory eventCreateDto) {
//        dao.save();

        EventConcert event = new ConcertMapper().concertFromDto(eventCreateDto);
        EventConcert concert = dao.findById(uuid).orElseThrow();
        dao.save(event);

    }
}
