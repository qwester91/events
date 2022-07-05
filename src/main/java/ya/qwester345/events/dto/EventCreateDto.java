package ya.qwester345.events.dto;

import org.springframework.context.annotation.Bean;
import ya.qwester345.events.dao.entity.EventStatus;
import ya.qwester345.events.dao.entity.EventType;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.UUID;

public class EventCreateDto {
    private String title;
    private String description;
    private LocalDateTime dtEvent;
    private LocalDateTime dtEndOfSale;
    private EventType type;
    private EventStatus status;
    private UUID currency;

    public EventCreateDto() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDtEvent(LocalDateTime dtEvent) {
        this.dtEvent = dtEvent;
    }

    public void setDtEndOfSale(LocalDateTime dtEndOfSale) {
        this.dtEndOfSale = dtEndOfSale;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public LocalDateTime getDtEndOfSale() {
        return dtEndOfSale;
    }

    public EventType getType() {
        return type;
    }

    public EventStatus getStatus() {
        return status;
    }

    public UUID getCurrency() {
        return currency;
    }
}
