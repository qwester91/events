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
import ya.qwester345.events.service.ServiceFactory;
import ya.qwester345.events.service.api.IEventService;
import ya.qwester345.events.service.api.IFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/afisha/event/")
public class EventController {

   private final IFactory factory;


    public EventController(IFactory factory) {
        this.factory = factory;
    }

    @PostMapping("/{type}")
    public ResponseEntity<Event> addEvent(@PathVariable(name = "type") String type ,@RequestBody EventCreateDto eventCreate){
        return new ResponseEntity<>(this.factory.add(EventType.valueOf(type), eventCreate), HttpStatus.CREATED);

    }



    @GetMapping("/{type}")
    public Page<Event> getEventsByType(@PathVariable String type, @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);

        return factory.getByType(EventType.valueOf(type), pageable);

    }
    @GetMapping("/{type}/{uuid}")
    public Event getEventsByUuid(@PathVariable(name = "type")String type,@PathVariable UUID uuid){
        return factory.getByUuid(EventType.valueOf(type),uuid);
    }

    @PutMapping("{type}/{uuid}/dt_update/{dt_update}")
    public void updateEvent(@PathVariable(name = "type")String type, @PathVariable(name = "uuid") UUID uuid,
                            @PathVariable(name = "dt_update") Long dtUpdate,
                            @RequestBody EventCreateDto eventCreateDto){
        LocalDateTime lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
        this.factory.update(EventType.valueOf(type), uuid, lastKnowDtUpdate, eventCreateDto);
    }




}
