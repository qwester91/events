package ya.qwester345.events.dao.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "events",schema = "event")
@SecondaryTable(name = "concert",pkJoinColumns = @PrimaryKeyJoinColumn(name = "uuid"),schema = "event")

public class EventConcert extends Event {


    private UUID category;

    public EventConcert() {
    }


    @Column(table = "concert")
    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }
}
