package ya.qwester345.events.dao.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dao.entity.EventConcert;
import ya.qwester345.events.dao.entity.EventFilm;
import ya.qwester345.events.dao.entity.enums.EventStatus;
import ya.qwester345.events.dao.entity.enums.EventType;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEventFilmDao extends JpaRepository<EventFilm, UUID> {


    Page<Event> findAllByType(EventType type, Pageable pageable);

    Page<Event> findAllByTypeAndStatusOrAuthor(EventType type, EventStatus status, String author, Pageable pageable);

    Page<Event> findAllByTypeAndStatus(EventType type, EventStatus status,Pageable pageable);

    Optional<EventFilm> findByUuidAndAuthor(UUID uuid, String author);

}
