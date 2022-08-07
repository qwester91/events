package ya.qwester345.events.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ya.qwester345.events.dao.api.IEventFilmDao;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.EventConcert;
import ya.qwester345.events.dao.entity.EventFilm;
import ya.qwester345.events.dao.entity.enums.EventStatus;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.*;
import ya.qwester345.events.dto.factory.EventDtoFactory;
import ya.qwester345.events.service.api.IEventService;
import ya.qwester345.events.service.api.IUserService;
import ya.qwester345.events.service.utils.EventMapper;
import ya.qwester345.events.service.utils.Validator;
import ya.qwester345.events.service.utils.api.IEventMapper;
import ya.qwester345.events.service.utils.api.IValidator;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Qualifier("EventFilmService")
public class EventFilmService implements IEventService<FilmReadDto> {
    private final IEventFilmDao dao;
    private final IValidator validator;
    private final IUserService userService;
    private final IEventMapper mapper;


    public EventFilmService(IEventFilmDao dao, IValidator validator, IUserService userService, IEventMapper mapper) {
        this.dao = dao;
        this.validator = validator;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public FilmReadDto add(EventDtoFactory dtoFactory) {
        UserDto userDto = userService.getUser();
        if (userDto!=null){
            for (GrantedAuthority authority : userDto.getAuthorities()) {
                if (!"ADMIN".equals(authority.getAuthority())) {
                    dtoFactory.setStatus(EventStatus.DRAFT);
                }
            }
    } FilmCreateDto dto = (FilmCreateDto) dtoFactory.getDto();
        validator.filmDtoValidate(dto);
        EventFilm event = new EventMapper().filmFromDto(dto);
        dao.save(event);
        return mapper.dtoFromFilmEntity(event);
    }

    @Override
    public ListOfEvents<Event> getByType(EventType type, Pageable pageable) {
        UserDto dto = userService.getUser();
        if (dto!=null){
        for (GrantedAuthority authority : dto.getAuthorities()) {
            if ("ADMIN".equals(authority.getAuthority())) {
                return new ListOfEvents<Event>(dao.findAllByType(type, pageable));
            }
        }

        return new ListOfEvents<Event>(dao.findAllByTypeAndStatusOrAuthor(type, EventStatus.PUBLISHED, dto.getUsername(), pageable));
        }
        return new ListOfEvents<Event>(dao.findAllByTypeAndStatus(type, EventStatus.PUBLISHED, pageable));
    }

    @Override
    public FilmReadDto getByUuid(UUID uuid) {
        return mapper.dtoFromFilmEntity(dao.findById(uuid).orElseThrow());
    }

    @Override
    @Transactional
    public FilmReadDto update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate,
                            EventDtoFactory dtoFactory) {
        UserDto userDto = userService.getUser();
        String name = userDto.getUsername();
        FilmCreateDto dto = (FilmCreateDto) dtoFactory.getDto();
        validator.filmDtoValidate(dto);
        for (GrantedAuthority authority : userDto.getAuthorities()) {
            if ("ADMIN".equals(authority.getAuthority())) {
                EventFilm film = dao.findById(uuid).orElseThrow();
                if (!film.getDtUpdate().equals(lastKnowDtUpdate)) {
                    throw new IllegalStateException("файл был изменен");
                } else {
                    film.setDuration(dto.getDuration());
                    film.setReleaseDate(dto.getReleaseDate());
                    film.setReleaseYear(dto.getReleaseYear());
                    film.setTitle(dto.getTitle());
                    film.setDescription(dto.getDescription());
                    film.setDtEvent(dto.getDtEvent());
                    film.setDtEndOfSale(dto.getDtEndOfSale());
                    film.setStatus(dto.getStatus());
                    film.setCurrency(dto.getCurrency());
                }
                dao.save(film);
                return mapper.dtoFromFilmEntity(film);
            }
        }
        EventFilm film = dao.findByUuidAndAuthor(uuid, name).orElseThrow();

        if (!film.getDtUpdate().equals(lastKnowDtUpdate)) {
            throw new IllegalStateException("файл был изменен");
        } else {
            film.setDuration(dto.getDuration());
            film.setReleaseDate(dto.getReleaseDate());
            film.setReleaseYear(dto.getReleaseYear());
            film.setTitle(dto.getTitle());
            film.setDescription(dto.getDescription());
            film.setDtEvent(dto.getDtEvent());
            film.setDtEndOfSale(dto.getDtEndOfSale());
            film.setStatus(EventStatus.DRAFT);
            film.setCurrency(dto.getCurrency());
        }
        dao.save(film);
        return mapper.dtoFromFilmEntity(film);
    }
}
