package ya.qwester345.events.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.EventConcert;
import ya.qwester345.events.dao.entity.EventFilm;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.EventCreateDto;
import ya.qwester345.events.service.api.IEventService;
import ya.qwester345.events.service.api.IFactory;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class ServiceFactory implements IFactory {
    @Qualifier("EventConcertService")
    private final IEventService<EventConcert> eventConcertService;

    @Qualifier("EventFilmService")
    private final IEventService<EventFilm> eventFilmService;

    public ServiceFactory(IEventService<EventConcert> eventConcertService, IEventService<EventFilm> eventFilmService) {
        this.eventConcertService = eventConcertService;
        this.eventFilmService = eventFilmService;
    }

    @Override
    public Event add(EventType type, EventCreateDto eventCreateDto) {
        Event add = null;
        if(type == EventType.CONCERTS) {
            add = eventConcertService.add(eventCreateDto);
        }else if(type == EventType.FILMS){
            add = eventFilmService.add(eventCreateDto);
        }
        return add;
    }

    @Override
    public Page<Event> getByType(EventType type, Pageable pageable) {
        Page<Event> eventPage = null;
        if(type == EventType.CONCERTS) {
            eventPage = eventConcertService.getByType(type,pageable);
        }else if(type == EventType.FILMS){
            eventPage = eventFilmService.getByType(type,pageable);
        }
        return eventPage;
    }

    @Override
    public Event getByUuid(EventType type, UUID uuid) {
        Event byUuid = null;
        if(type == EventType.CONCERTS) {
            byUuid = eventConcertService.getByUuid(uuid);
        }else if(type == EventType.FILMS){
            byUuid = eventFilmService.getByUuid(uuid);
        }

        return byUuid;
    }

    @Override
    public void update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate, EventCreateDto eventCreateDto) {
        if(type == EventType.CONCERTS) {
           eventConcertService.update(type, uuid, lastKnowDtUpdate, eventCreateDto);
        }else if(type == EventType.FILMS){
            eventFilmService.update(type, uuid, lastKnowDtUpdate, eventCreateDto);
        }
    }
}
