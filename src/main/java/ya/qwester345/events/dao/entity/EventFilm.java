package ya.qwester345.events.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "events",schema = "event")
@SecondaryTable(name = "film",pkJoinColumns = @PrimaryKeyJoinColumn(name = "uuid"),schema = "event")

public class EventFilm extends Event{

    private UUID country;

    private Integer releaseYear;

    private LocalDate releaseDate;

    private Integer duration;

    public EventFilm() {
    }
    @Column(table = "film")

    public UUID getCountry() {
        return country;
    }

    public void setCountry(UUID country) {
        this.country = country;
    }
    @Column(table = "film")
    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
    @Column(table = "film")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    @Column(table = "film")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
