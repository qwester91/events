package ya.qwester345.events.dto.api;

import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dto.PageOfEvents;

import java.util.List;

public interface IPageOfEventsBuilder {

    void setNumber (int number);
    void setSize(int size);
    void setTotalPages(int totalPages);
    void setTotalElements(int totalElements);
    void setFirst(boolean first);
    void setNumberOfElements(int numberOfElements);
    void setLast(boolean last);
    void setEvents(List<Event> events);
    PageOfEvents build();
}
