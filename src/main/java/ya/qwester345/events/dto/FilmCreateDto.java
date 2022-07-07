package ya.qwester345.events.dto;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.UUID;

public class FilmCreateDto extends EventCreateDto{

    private UUID country;
    private Integer releaseYear;
    private LocalDate releaseDate;
    private Integer duration;

    public FilmCreateDto(UUID country, Integer releaseYear, LocalDate releaseDate, Integer duration) {
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
