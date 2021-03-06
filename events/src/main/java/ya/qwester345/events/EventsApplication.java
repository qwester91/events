package ya.qwester345.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ya.qwester345.events.dao.api.IEventConcertDao;
import ya.qwester345.events.dao.api.IEventFilmDao;
import ya.qwester345.events.dao.entity.EventConcert;
import ya.qwester345.events.dao.entity.enums.EventStatus;
import ya.qwester345.events.dao.entity.enums.EventType;
import ya.qwester345.events.dto.factory.EventDtoFactory;
import ya.qwester345.events.service.EventConcertService;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
@EnableJpaRepositories
public class EventsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsApplication.class, args);



    }

}
