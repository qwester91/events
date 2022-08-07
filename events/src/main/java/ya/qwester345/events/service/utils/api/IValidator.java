package ya.qwester345.events.service.utils.api;

import ya.qwester345.events.dto.ConcertCreateDto;
import ya.qwester345.events.dto.FilmCreateDto;

public interface IValidator {

    void concertDtoValidate(ConcertCreateDto dto);
    void filmDtoValidate(FilmCreateDto dto);
}
