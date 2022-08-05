package ya.qwester345.events.dto;

import ya.qwester345.events.dao.entity.enums.EventStatus;
import ya.qwester345.events.dao.entity.enums.EventType;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConcertCreateDto extends EventCreateDto{

    private UUID category;


    public ConcertCreateDto(String title, String description, LocalDateTime dtEvent, LocalDateTime dtEndOfSale, EventType type, EventStatus status, UUID currency, UUID category, String author) {
        super(title, description, dtEvent, dtEndOfSale, type, status, currency, author);
        this.category = category;
    }

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }
}
