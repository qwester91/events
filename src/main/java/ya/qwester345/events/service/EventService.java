package ya.qwester345.events.service;

import org.springframework.stereotype.Service;
import ya.qwester345.events.dao.api.IEventDao;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.EventType;
import ya.qwester345.events.dto.EventCreateDto;
import ya.qwester345.events.dto.PageOfEvents;
import ya.qwester345.events.service.api.IEventService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EventService implements IEventService {
    private final IEventDao dao;

    public EventService(IEventDao dao) {
        this.dao = dao;
    }

    @Override
    public Event add(EventCreateDto eventCreate) {
        return null;
    }

    @Override
    public PageOfEvents getByType(EventType type, Integer page, Integer size) {
        return null;
    }

    @Override
    public Event getByUuid(UUID uuid) {
        return dao.findEventByUuid(uuid);
    }

    @Override
    public void update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate, EventCreateDto eventCreateDto) {

    }
}
