package ya.qwester345.events.service.factory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.EventConcert;
import ya.qwester345.events.dao.entity.EventFilm;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.ConcertCreateDto;
import ya.qwester345.events.dto.EventCreateDto;
import ya.qwester345.events.dto.ListOfEvents;
import ya.qwester345.events.dto.factory.EventDtoFactory;
import ya.qwester345.events.service.EventConcertService;
import ya.qwester345.events.service.EventFilmService;
import ya.qwester345.events.service.api.IEventService;
import ya.qwester345.events.service.api.IFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
public class ServiceFactory implements IFactory {

    private final List<IEventService> listOfServices;
//    @Qualifier("EventConcertService")
//    private final IEventService<EventConcert> eventConcertService;
//
//    @Qualifier("EventFilmService")
//    private final IEventService<EventFilm> eventFilmService;


    public ServiceFactory(List<IEventService> listOfServices) {
        this.listOfServices = listOfServices;
    }

    private IEventService<? extends Event> getService(EventType type) {
            IEventService service = null;
        if(type.equals(EventType.CONCERTS)) {
            for (IEventService listOfService : listOfServices) {
                if (listOfService instanceof EventConcertService){
                    service = listOfService;
                }
           }
        }else if(type == EventType.FILMS){
            for (IEventService listOfService : listOfServices) {
                if (listOfService instanceof EventFilmService){
                    service = listOfService;
                }
            }
        }
        return service;
    }

    @Override
    public Event add(EventType type, EventDtoFactory eventCreateDto) {
        IEventService<? extends Event> service = getService(type);
        Event event = service.add(eventCreateDto);
        return event;
    }

    @Override
    public ListOfEvents<Event> getByType(EventType type, Pageable pageable) {
        IEventService<? extends Event> service = getService(type);
        ListOfEvents<Event> eventPage = service.getByType(type, pageable);
        return eventPage;
    }

    @Override
    public Event getByUuid(EventType type, UUID uuid) {
        IEventService<? extends Event> service = getService(type);
        Event event = service.getByUuid(uuid);
        return event;
    }

    @Override
    public void update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate, EventDtoFactory eventCreateDto) {
        IEventService<? extends Event> service = getService(type);
        Event event = service.update(type, uuid, lastKnowDtUpdate, eventCreateDto);
    }
}
