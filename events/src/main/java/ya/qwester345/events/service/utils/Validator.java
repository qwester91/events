package ya.qwester345.events.service.utils;

import org.springframework.stereotype.Component;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.ConcertCreateDto;
import ya.qwester345.events.dto.EventCreateDto;
import ya.qwester345.events.dto.FilmCreateDto;
import ya.qwester345.events.service.exeptions.InvalidDtoException;
import ya.qwester345.events.service.utils.api.IValidator;
import ya.qwester345.events.service.utils.httpClients.HttpClientClassifiers;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Component
public class Validator implements IValidator {

    private final HttpClientClassifiers clientClassifiers;

    public Validator(HttpClientClassifiers valid) {
        this.clientClassifiers = valid;
    }

    public void concertDtoValidate(ConcertCreateDto dto){
        if (EventType.CONCERTS.equals(dto.getType())){
            if (!clientClassifiers.isCategoryExistInClassifiers(dto.getCategory())){
                throw new InvalidDtoException("Category", "не существует такой категории");
            }
            validate(dto);
        }
    }

    public void filmDtoValidate(FilmCreateDto dto){
        if (EventType.FILMS.equals(dto.getType())){
            if (!clientClassifiers.isCountryExistInClassifiers(dto.getCountry())){
                throw new InvalidDtoException("Country", "не существует такой страны");
            }
            if (dto.getDuration() < 0){
                throw new InvalidDtoException("Duration", "из за отрицательной длительности фильма вселенная схлопнется");
            }
            if (dto.getReleaseYear()> LocalDate.now().getYear()){
                throw new InvalidDtoException("releaseYear", "а фильм то еще не вышел");
            }
          validate(dto);
        }
    }
    private void validate(EventCreateDto dto){
        if (dto.getTitle()==null|| dto.getTitle().equals("")){
            throw new InvalidDtoException("title", "title can't be empty");
        }
        if (dto.getDescription()==null|| dto.getDescription().equals("")){
            throw new InvalidDtoException("description", "description can't be empty");
        }
        if (dto.getDtEvent()==null){
            throw new InvalidDtoException("dtEvent", "Date of event can't be empty");
        }
        if (dto.getDtEvent().compareTo(LocalDateTime.now()) < 0 ){
            throw new InvalidDtoException("dtEvent","Event is все, нельзя создать прошедшее событие");
        }
        if (dto.getDtEndOfSale().compareTo(dto.getDtEvent()) < 0 ){
            throw new InvalidDtoException("DtEndOfSale", "вы шо, продаете билеты на уже уехавший поезд?");
        }
    }
}
