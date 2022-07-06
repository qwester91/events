package ya.qwester345.events.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "eventFilm", schema = "events")
public class EventFilm  {
    @Id
    private UUID uuid;
    @Column
    private UUID country;
    @Column
    private Integer releaseYear;
    @Column
    private LocalDate releaseDate;
    @Column
    private Integer duration;

    public EventFilm() {
    }

//    public EventFilm(UUID uuid, UUID country, Integer releaseYear, LocalDate releaseDate, Integer duration) {
//        this.uuid = uuid;
//        this.country = country;
//        this.releaseYear = releaseYear;
//        this.releaseDate = releaseDate;
//        this.duration = duration;
//    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
