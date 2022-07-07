package ya.qwester345.events.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.EventConcert;
import ya.qwester345.events.dao.entity.EventFilm;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.ConcertCreateDto;
import ya.qwester345.events.dto.EventCreateDto;
import ya.qwester345.events.dto.FilmCreateDto;
import ya.qwester345.events.service.EventConcertService;
import ya.qwester345.events.service.EventFilmService;
import ya.qwester345.events.service.api.IEventService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/afisha/event/")
public class EventController {

    @Qualifier("EventConcertService")
    private final IEventService<EventConcert> eventConcertService;

    @Qualifier("EventFilmService")
    private final IEventService<EventFilm> eventFilmService;


    public EventController(IEventService<EventConcert> eventConcertService, IEventService<EventFilm> eventFilmService) {
        this.eventConcertService = eventConcertService;
        this.eventFilmService = eventFilmService;
    }

    @PostMapping("/CONCERTS")
    public ResponseEntity<EventConcert> addConcert(@RequestBody ConcertCreateDto eventCreate){
        return new ResponseEntity<>(this.eventConcertService.add(eventCreate), HttpStatus.CREATED);

    }

    @PostMapping("/FILMS")
    public ResponseEntity<EventFilm> addFilm( @RequestBody FilmCreateDto eventCreate){
        return new ResponseEntity<>(this.eventFilmService.add(eventCreate), HttpStatus.CREATED);

    }

    @GetMapping("/{type}")
    public Page<Event> getEventsByType(@PathVariable EventType type, @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        if (type == EventType.CONCERTS){
            return eventConcertService.getByType(type,pageable);
        }
        return eventFilmService.getByType(type, pageable);

    }
    @GetMapping("/{uuid}")
    public EventConcert getEventsByUuid(@PathVariable UUID uuid){
        return eventConcertService.getByUuid(uuid);
    }

    @PutMapping("{type}/{uuid}/dt_update/{dt_update}")
    public void updateEvent(@PathVariable(name = "type") EventType type, @PathVariable(name = "uuid") UUID uuid,
                            @PathVariable(name = "dt_update") Long dtUpdate,
                            @RequestBody EventCreateDto eventCreateDto){
        LocalDateTime lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
        this.eventConcertService.update(type, uuid, lastKnowDtUpdate, eventCreateDto);
    }



}
