package ya.qwester345.events.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.EventCreateDto;
import ya.qwester345.events.dto.PageOfEvents;
import ya.qwester345.events.service.api.IEventService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/afisha/event/")
public class EventController {
    private final IEventService <Event> eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }


    @PostMapping("/{type}")
    public ResponseEntity<Event> addEvent(@PathVariable String type, @RequestBody EventCreateDto eventCreate){
        return new ResponseEntity<>(this.eventService.add(eventCreate, EventType.valueOf(type)), HttpStatus.CREATED);

    }

    @GetMapping("/{type}")
    public PageOfEvents getEventsByType(@PathVariable EventType type, @RequestParam("page") Integer page,
                                        @RequestParam("size") Integer size){
        return eventService.getByType(type, page, size);

    }
    @GetMapping("/{uuid}")
    public Event getEventsByUuid(@PathVariable UUID uuid){
        return eventService.getByUuid(uuid);
    }

    @PutMapping("{type}/{uuid}/dt_update/{dt_update}")
    public void updateEvent(@PathVariable(name = "type") EventType type, @PathVariable(name = "uuid") UUID uuid,
                            @PathVariable(name = "dt_update") Long dtUpdate,
                            @RequestBody EventCreateDto eventCreateDto){
        LocalDateTime lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
        this.eventService.update(type, uuid, lastKnowDtUpdate, eventCreateDto);
    }



}
