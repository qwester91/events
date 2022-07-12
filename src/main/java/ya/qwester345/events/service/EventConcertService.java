package ya.qwester345.events.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ya.qwester345.events.dao.api.IEventConcertDao;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.EventConcert;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.ListOfEvents;
import ya.qwester345.events.dto.factory.EventDtoFactory;
import ya.qwester345.events.service.api.IEventService;
import ya.qwester345.events.service.utils.EventMapper;

import java.time.LocalDateTime;
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
        EventConcert event = new EventMapper().concertFromDto(eventCreate);
        dao.save(event);
        return event;
    }

    @Override
    public ListOfEvents<Event> getByType(EventType type, Pageable pageable) {
        return new ListOfEvents<>(dao.findAllByType(type, pageable));
    }

    @Override
    public EventConcert getByUuid(UUID uuid) {
        return dao.findById(uuid).orElseThrow();

    }

    @Override
    public EventConcert update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate, EventDtoFactory eventCreateDto) {
//        dao.save();

        EventConcert concert = dao.findById(uuid).orElseThrow();
        LocalDateTime dtCreate = concert.getDtCreate();
        if (!concert.getDtUpdate().equals(lastKnowDtUpdate)){
            throw new IllegalStateException("файл был изменен");
        }else {
            concert = new EventMapper().concertFromDto(eventCreateDto);
            concert.setUuid(uuid);
            concert.setDtCreate(dtCreate);
        }
        dao.save(concert);
        return concert;

    }
}
