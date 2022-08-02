package ya.qwester345.events.dao.entity;

import org.hibernate.annotations.GenericGenerator;
import ya.qwester345.events.dao.entity.enums.EventStatus;
import ya.qwester345.events.dao.entity.enums.EventType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.UUID;


@MappedSuperclass
public class Event {
    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String title;
    private String description;
    private LocalDateTime dtEvent;
    private LocalDateTime dtEndOfSale;
    private EventType type;
    private EventStatus status;
    private UUID currency;
    private String author;


    public Event() {
    }
    @Id
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Column(name = "dt_create")
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Version
    @Column(name = "dt_update")
    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    @Column (name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "dt_event")
    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public void setDtEvent(LocalDateTime dtEvent) {
        this.dtEvent = dtEvent;
    }

    @Column(name = "dt_end_of_sale")
    public LocalDateTime getDtEndOfSale() {
        return dtEndOfSale;
    }

    public void setDtEndOfSale(LocalDateTime dtEndOfSale) {
        this.dtEndOfSale = dtEndOfSale;
    }

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    @Column(name = "currency")
    public UUID getCurrency() {
        return currency;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
    }
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
