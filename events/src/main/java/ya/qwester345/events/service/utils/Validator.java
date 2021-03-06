package ya.qwester345.events.service.utils;

import org.springframework.stereotype.Component;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.ConcertCreateDto;
import ya.qwester345.events.dto.factory.EventDtoFactory;
import ya.qwester345.events.service.exeptions.InvalidDtoException;

import java.time.LocalDateTime;

public class Validator{

    private final HttpClientValid valid;

    public Validator(HttpClientValid valid) {
        this.valid = valid;
    }

    public void concertDtoValidate(ConcertCreateDto factory){
        if (EventType.CONCERTS.equals(factory.getType())){
            if (factory.getTitle()==null|| factory.getTitle().equals("")){
                throw new InvalidDtoException("title can't be empty");
            }
            if (factory.getDescription()==null|| factory.getDescription().equals("")){
                throw new InvalidDtoException("description can't be empty");
            }
            if (factory.getDtEvent()==null){
                throw new InvalidDtoException("Date of event can't be empty");
            }
            if (factory.getDtEvent().compareTo(LocalDateTime.now()) < 0 ){
                throw new InvalidDtoException("Event is все, нельзя создать прошедшее событие");
            }
            if (factory.getDtEndOfSale().compareTo(factory.getDtEvent()) < 0 ){
                throw new InvalidDtoException("вы шо, продаете билеты на уже уехавший поезд?");
            }
            if (!valid.isExistInClassifiers("http://localhost:81/api/v1/classifier/concert/category/", factory.getCategory())){
                throw new InvalidDtoException("не существует такой категории");
            }

        }


    }
}
