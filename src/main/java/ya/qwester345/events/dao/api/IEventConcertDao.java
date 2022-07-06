package ya.qwester345.events.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.EventConcert;

import java.util.UUID;

@Repository
public interface IEventConcertDao extends JpaRepository<EventConcert, UUID> {



}
