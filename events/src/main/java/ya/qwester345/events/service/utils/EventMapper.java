package ya.qwester345.events.service.utils;

import org.springframework.stereotype.Component;
import ya.qwester345.events.dao.entity.EventConcert;
import ya.qwester345.events.dao.entity.EventFilm;
import ya.qwester345.events.dto.ConcertCreateDto;
import ya.qwester345.events.dto.ConcertReadDto;
import ya.qwester345.events.dto.FilmCreateDto;
import ya.qwester345.events.dto.FilmReadDto;
import ya.qwester345.events.dto.factory.EventDtoFactory;
import ya.qwester345.events.service.utils.api.IEventMapper;

import java.time.LocalDateTime;
import java.util.UUID;
@Component
public class EventMapper implements IEventMapper {

    public EventConcert concertFromDto(ConcertCreateDto eventCreateDto){
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
        concert.setAuthor(eventCreateDto.getAuthor());
        return concert;
    }

    public EventFilm filmFromDto(FilmCreateDto eventCreateDto) {
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
        film.setAuthor(eventCreateDto.getAuthor());
        return film;
    }

    @Override
    public FilmReadDto dtoFromFilmEntity(EventFilm eventFilm) {
        FilmReadDto dto = new FilmReadDto(eventFilm.getUuid(),
                eventFilm.getDtCreate(),
                eventFilm.getDtUpdate(),
                eventFilm.getTitle(),
                eventFilm.getDescription(),
                eventFilm.getDtEvent(),
                eventFilm.getDtEndOfSale(),
                eventFilm.getType(),
                eventFilm.getStatus(),
                eventFilm.getCurrency(),
                eventFilm.getCountry(),
                eventFilm.getReleaseYear(),
                eventFilm.getReleaseDate(),
                eventFilm.getDuration());
        return dto;
    }

    @Override
    public ConcertReadDto dtoFromConcertEntity(EventConcert eventConcert) {
       ConcertReadDto dto = new ConcertReadDto(
                eventConcert.getUuid(),
                eventConcert.getDtCreate(),
                eventConcert.getDtUpdate(),
                eventConcert.getTitle(),
                eventConcert.getDescription(),
                eventConcert.getDtEvent(),
                eventConcert.getDtEndOfSale(),
                eventConcert.getType(),
                eventConcert.getStatus(),
                eventConcert.getCurrency(),
                eventConcert.getCategory());
        return dto;
    }
}
