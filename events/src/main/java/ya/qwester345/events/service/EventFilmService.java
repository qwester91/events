package ya.qwester345.events.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ya.qwester345.events.dao.api.IEventFilmDao;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.EventFilm;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.FilmCreateDto;
import ya.qwester345.events.dto.ListOfEvents;
import ya.qwester345.events.dto.factory.EventDtoFactory;
import ya.qwester345.events.service.api.IEventService;
import ya.qwester345.events.service.utils.EventMapper;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Qualifier("EventFilmService")
public class EventFilmService implements IEventService<EventFilm> {
    private final IEventFilmDao dao;

    public EventFilmService(IEventFilmDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public EventFilm add(EventDtoFactory dtoFactory) {
        FilmCreateDto dto = (FilmCreateDto) dtoFactory.getDto();
        EventFilm event = new EventMapper().filmFromDto(dto);
        dao.save(event);
        return event;
    }

    @Override
    public ListOfEvents<Event> getByType(EventType type, Pageable pageable) {
        return new ListOfEvents<>(dao.findAllByType(type, pageable));
    }

    @Override
    public EventFilm getByUuid(UUID uuid) {
        return dao.findById(uuid).orElseThrow();
    }

    @Override
    @Transactional
    public EventFilm update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate, EventDtoFactory dtoFactory) {
        EventFilm film = dao.findById(uuid).orElseThrow();
        FilmCreateDto dto = (FilmCreateDto) dtoFactory.getDto();
        if (!film.getDtUpdate().equals(lastKnowDtUpdate)){
            throw new IllegalStateException("файл был изменен");
        }else {
            film.setCountry(dto.getCountry());
            film.setDuration(dto.getDuration());
            film.setReleaseDate(dto.getReleaseDate());

        }
        dao.save(film);
        return film;
    }
}
