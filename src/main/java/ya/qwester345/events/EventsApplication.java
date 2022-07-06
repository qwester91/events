package ya.qwester345.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ya.qwester345.events.dao.api.IEventConcertDao;
import ya.qwester345.events.dao.api.IEventFilmDao;

@SpringBootApplication
@EnableJpaRepositories(
        basePackageClasses = {
                IEventConcertDao.class, IEventConcertDao.class
        })
public class EventsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsApplication.class, args);
    }

}
