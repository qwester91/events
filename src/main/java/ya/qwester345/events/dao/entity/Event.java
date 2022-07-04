package ya.qwester345.events.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event", schema = "events")
public class Event extends BaseEssence {
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private LocalDateTime dtEvent;
    @Column
    private LocalDateTime dtEndOfSale;
    @Column
    private EventType type;
    @Column
    private EventStatus status;
    @Column
    private UUID currency;

    public Event() {
    }

    public Event(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                 String title, String description, LocalDateTime dtEvent,
                 LocalDateTime atEndOfSale, EventType type, EventStatus status,
                 UUID currency) {
        super(uuid, dtCreate, dtUpdate);
        this.title = title;
        this.description = description;
        this.dtEvent = dtEvent;
        this.dtEndOfSale = atEndOfSale;
        this.type = type;
        this.status = status;
        this.currency = currency;
    }
}
