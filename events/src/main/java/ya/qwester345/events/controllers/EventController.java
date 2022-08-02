package ya.qwester345.events.controllers;

import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.enums.EventStatus;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.EventCreateDto;
import ya.qwester345.events.dto.ListOfEvents;
import ya.qwester345.events.dto.factory.EventDtoFactory;
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
    public ResponseEntity<Event> addEvent(@PathVariable(name = "type") String type ,@RequestBody EventDtoFactory eventCreate){

        return new ResponseEntity<>(this.factory.add(EventType.valueOf(type), eventCreate), HttpStatus.CREATED);

    }



    @GetMapping("/{type}")
    public ListOfEvents<Event> getEventsByType(@PathVariable String type, @RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
    @RequestHeader(value = "Authorization") String token){

        Pageable pageable = PageRequest.of(page-1, size);

        return factory.getByType(EventType.valueOf(type), pageable);

    }
    @GetMapping("/{type}/{uuid}")
    public Event getEventsByUuid(@PathVariable(name = "type")String type,@PathVariable UUID uuid){
        return factory.getByUuid(EventType.valueOf(type),uuid);
    }
    @GetMapping("/test")
    public EventCreateDto getEventsByUuid(){
        EventDtoFactory concert = new EventDtoFactory();

        concert.setType(EventType.CONCERTS);
        concert.setTitle("Sting");
        concert.setDtEvent(LocalDateTime.now());
        concert.setStatus(EventStatus.PUBLISHED);
        concert.setDescription("songs");
        concert.setCurrency(UUID.randomUUID());
        concert.setCategory(UUID.randomUUID());
        concert.setDtEndOfSale(LocalDateTime.now());
        return concert.getDto();

    }

    @PutMapping("{type}/{uuid}/dt_update/{dt_update}")
    public void updateEvent(@PathVariable(name = "type")String type, @PathVariable(name = "uuid") UUID uuid,
                            @PathVariable(name = "dt_update") Long dtUpdate,
                            @RequestBody EventDtoFactory eventCreateDto){
        LocalDateTime lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
        this.factory.update(EventType.valueOf(type), uuid, lastKnowDtUpdate, eventCreateDto);
    }




}
