package ya.qwester345.events.dto;

import ya.qwester345.events.dao.entity.enums.EventStatus;
import ya.qwester345.events.dao.entity.enums.EventType;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class FilmCreateDto extends EventCreateDto{

    private UUID country;
    private Integer releaseYear;
    private LocalDate releaseDate;
    private Integer duration;

    public FilmCreateDto(String title, String description, LocalDateTime dtEvent,
                         LocalDateTime dtEndOfSale, EventType type, EventStatus status,
                         UUID currency, UUID country, Integer releaseYear,
                         LocalDate releaseDate, Integer duration, String author) {
        super(title, description, dtEvent, dtEndOfSale, type, status, currency, author);
        this.country = country;
        this.releaseYear = releaseYear;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public UUID getCountry() {
        return country;
    }

    public void setCountry(UUID country) {
        this.country = country;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
