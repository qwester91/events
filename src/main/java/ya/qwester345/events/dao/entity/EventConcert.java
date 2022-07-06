package ya.qwester345.events.dao.entity;

import javax.persistence.*;
import java.util.UUID;
@Entity

@Table(name = "eventConcert", schema = "events")
public class EventConcert {
    @Id
    private UUID uuid;
    @Column
    private UUID category;

    public EventConcert() {
    }

   // public EventConcert(UUID uuid, UUID category) {
   //     this.uuid = uuid;
   //     this.category = category;
   // }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }
}
