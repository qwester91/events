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
import ya.qwester345.events.dto.ReadDto;
import ya.qwester345.events.dto.UserDto;
import ya.qwester345.events.dto.factory.EventDtoFactory;
import ya.qwester345.events.service.UsersService;
import ya.qwester345.events.service.api.IFactory;
import ya.qwester345.events.service.api.IUserService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/afisha/event/")
public class EventController {

   private final IFactory factory;
   private final IUserService userService;


    public EventController(IFactory factory, IUserService userService) {
        this.factory = factory;
        this.userService = userService;
    }

    @PostMapping("/{type}")
    public ResponseEntity<ReadDto> addEvent(@PathVariable(name = "type") String type ,@RequestBody EventDtoFactory eventCreate){
        UserDto user = userService.getUser();
        eventCreate.setAuthor(user.getUsername());


        return new ResponseEntity<ReadDto>(this.factory.add(EventType.valueOf(type), eventCreate), HttpStatus.CREATED);

    }



    @GetMapping("/{type}")
    public ListOfEvents<Event> getEventsByType(@PathVariable String type, @RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size){

        Pageable pageable = PageRequest.of(page-1, size);

        return factory.getByType(EventType.valueOf(type), pageable);

    }
    @GetMapping("/{type}/{uuid}")
    public ReadDto getEventsByUuid(@PathVariable(name = "type")String type, @PathVariable UUID uuid){
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
