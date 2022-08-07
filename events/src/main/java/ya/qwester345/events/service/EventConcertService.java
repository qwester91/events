package ya.qwester345.events.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ya.qwester345.events.dao.api.IEventConcertDao;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.EventConcert;
import ya.qwester345.events.dao.entity.enums.EventStatus;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.*;
import ya.qwester345.events.dto.factory.EventDtoFactory;
import ya.qwester345.events.service.api.IEventService;
import ya.qwester345.events.service.api.IUserService;
import ya.qwester345.events.service.utils.EventMapper;
import ya.qwester345.events.service.utils.api.IEventMapper;
import ya.qwester345.events.service.utils.api.IValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Qualifier("EventConcertService")
public class EventConcertService implements IEventService <ConcertReadDto> {
    private final IEventConcertDao dao;
    private final IValidator validator;
    private final IUserService userService;
    private final IEventMapper mapper;

    public EventConcertService(IEventConcertDao dao, IValidator validator, IUserService userService, IEventMapper mapper) {
        this.dao = dao;
        this.validator = validator;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ConcertReadDto add(EventDtoFactory dtoFactory) {
        UserDto userDto = userService.getUser();
        if (userDto!=null){
            for (GrantedAuthority authority : userDto.getAuthorities()) {
                if (!"ADMIN".equals(authority.getAuthority())) {
                    dtoFactory.setStatus(EventStatus.DRAFT);
                }
            }
        }
        ConcertCreateDto dto = (ConcertCreateDto) dtoFactory.getDto();
        validator.concertDtoValidate(dto);
        EventConcert event = new EventMapper().concertFromDto(dto);
        dao.save(event);
        return mapper.dtoFromConcertEntity(event);
    }


    @Override
    public ListOfEvents<Event> getByType(EventType type, Pageable pageable) {
        UserDto dto = userService.getUser();
        if(dto!=null){
        for (GrantedAuthority authority : dto.getAuthorities()) {
            if("ADMIN".equals(authority.getAuthority())){

                return new ListOfEvents<Event>(dao.findAllByType(type, pageable));
            }
        }

        return new ListOfEvents<Event>(dao.findAllByTypeAndStatusOrAuthor(type, EventStatus.PUBLISHED, dto.getUsername(), pageable));
        }
        return new ListOfEvents<Event>(dao.findAllByTypeAndStatus(type, EventStatus.PUBLISHED, pageable));
    }


    @Override
    public ConcertReadDto getByUuid(UUID uuid) {
        return mapper.dtoFromConcertEntity(dao.findById(uuid).orElseThrow());

    }

    @Override
    @Transactional
    public ConcertReadDto update(EventType type, UUID uuid, LocalDateTime lastKnowDtUpdate, EventDtoFactory dtoFactory) {
        UserDto userDto = userService.getUser();
        String name = userDto.getUsername();
        ConcertCreateDto dto = (ConcertCreateDto) dtoFactory.getDto();
        validator.concertDtoValidate(dto);
        for (GrantedAuthority authority : userDto.getAuthorities()) {
            if("ADMIN".equals(authority.getAuthority())){
                EventConcert concert = dao.findById(uuid).orElseThrow();
                if (!concert.getDtUpdate().truncatedTo(ChronoUnit.MILLIS).equals(lastKnowDtUpdate.truncatedTo(ChronoUnit.MILLIS))){
                    throw new IllegalStateException("файл был изменен");
                }else {
                    concert.setCategory(dto.getCategory());
                    concert.setTitle(dto.getTitle());
                    concert.setDescription(dto.getDescription());
                    concert.setDtEvent(dto.getDtEvent());
                    concert.setDtEndOfSale(dto.getDtEndOfSale());
                    concert.setStatus(dto.getStatus());
                    concert.setCurrency(dto.getCurrency());
                }
                dao.save(concert);
                return mapper.dtoFromConcertEntity(concert);
            }
        }
        EventConcert concert = dao.findByUuidAndAuthor(uuid, name).orElseThrow();

        if (!concert.getDtUpdate().truncatedTo(ChronoUnit.MILLIS).equals(lastKnowDtUpdate.truncatedTo(ChronoUnit.MILLIS))){
            throw new IllegalStateException("файл был изменен");
        }else {
            concert.setCategory(dto.getCategory());
            concert.setTitle(dto.getTitle());
            concert.setDescription(dto.getDescription());
            concert.setDtEvent(dto.getDtEvent());
            concert.setDtEndOfSale(dto.getDtEndOfSale());
            concert.setStatus(EventStatus.DRAFT);
            concert.setCurrency(dto.getCurrency());
        }
        dao.save(concert);
        return mapper.dtoFromConcertEntity(concert);
    }
}
