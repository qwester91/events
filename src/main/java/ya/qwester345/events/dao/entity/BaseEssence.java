package ya.qwester345.events.dao.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
public class BaseEssence {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID uuid;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    public BaseEssence() {
    }

    public BaseEssence(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }
}
