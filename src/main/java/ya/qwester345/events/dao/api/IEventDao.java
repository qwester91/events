package ya.qwester345.events.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.EventType;

import java.util.UUID;

@Repository
public interface IEventDao extends JpaRepository<Event, UUID> {
    @Query
    Event getEventByType(EventType type);
    @Query
    Event findEventByUuid(UUID uuid);
}
