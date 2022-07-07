package ya.qwester345.events.dao.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.enums.EventType;

import java.util.UUID;

public interface IEventDao extends JpaRepository<Event, UUID> {
    @Query
    Page<Event> findAllByActionType(@Param("type") EventType type,
                                    Pageable pageable);
}
