package ya.qwester345.events.service.utils;

import ya.qwester345.events.dao.entity.EventConcert;
import ya.qwester345.events.dao.entity.EventFilm;
import ya.qwester345.events.dto.factory.EventDtoFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventMapper {
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

    public EventFilm filmFromDto(EventDtoFactory eventCreateDto) {
        EventFilm film = new EventFilm();
        film.setDuration(eventCreateDto.getDuration());
        film.setReleaseDate(eventCreateDto.getReleaseDate());
        film.setReleaseYear(eventCreateDto.getReleaseYear());
        film.setCurrency(eventCreateDto.getCurrency());
        film.setDescription(eventCreateDto.getDescription());
        film.setDtCreate(LocalDateTime.now());
        film.setDtUpdate(film.getDtCreate());
        film.setDtEndOfSale(eventCreateDto.getDtEndOfSale());
        film.setDtEvent(eventCreateDto.getDtEvent());
        film.setStatus(eventCreateDto.getStatus());
        film.setTitle(eventCreateDto.getTitle());
        film.setType(eventCreateDto.getType());
        film.setUuid(UUID.randomUUID());
        return film;
    }
}
