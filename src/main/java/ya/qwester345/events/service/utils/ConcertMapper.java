package ya.qwester345.events.service.utils;

import ya.qwester345.events.dao.entity.EventConcert;
import ya.qwester345.events.dto.ConcertCreateDto;
import ya.qwester345.events.dto.EventCreateDto;
import ya.qwester345.events.dto.factory.EventDtoFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConcertMapper {
    public EventConcert concertFromDto(EventDtoFactory eventCreateDto){
        EventConcert concert = new EventConcert();
        concert.setCategory(eventCreateDto.getCategory());
        concert.setCurrency(eventCreateDto.getCurrency());
        concert.setDescription(eventCreateDto.getDescription());
        concert.setDtCreate(LocalDateTime.now());
        concert.setDtUpdate(concert.getDtCreate());
        concert.setDtEndOfSale(eventCreateDto.getDtEndOfSale());
        concert.setDtEvent(eventCreateDto.getDtEvent());
        concert.setStatus(eventCreateDto.getStatus());
        concert.setTitle(eventCreateDto.getTitle());
        concert.setType(eventCreateDto.getType());
        concert.setUuid(UUID.randomUUID());
        return concert;
    }
}
