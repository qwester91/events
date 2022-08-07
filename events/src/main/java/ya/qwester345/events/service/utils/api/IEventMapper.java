package ya.qwester345.events.service.utils.api;

import ya.qwester345.events.dao.entity.EventConcert;
import ya.qwester345.events.dao.entity.EventFilm;
import ya.qwester345.events.dto.ConcertCreateDto;
import ya.qwester345.events.dto.ConcertReadDto;
import ya.qwester345.events.dto.FilmCreateDto;
import ya.qwester345.events.dto.FilmReadDto;

public interface IEventMapper {
    EventConcert concertFromDto(ConcertCreateDto eventCreateDto);

    EventFilm filmFromDto(FilmCreateDto eventCreateDto);

    FilmReadDto dtoFromFilmEntity(EventFilm eventFilm);
    ConcertReadDto dtoFromConcertEntity(EventConcert eventConcert);
}
