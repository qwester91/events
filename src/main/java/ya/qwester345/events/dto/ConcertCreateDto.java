package ya.qwester345.events.dto;

import java.util.UUID;

public class ConcertCreateDto extends EventCreateDto{
    private UUID category;

    public ConcertCreateDto(UUID category) {
        this.category = category;
    }

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }
}
