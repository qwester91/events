package ya.qwester345.events.dto.api;

import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dto.PageOfEvents;
import ya.qwester345.events.dto.PageOfEventsBuilder;

import java.util.List;

public interface IPageOfEventsBuilder {

   PageOfEventsBuilder setNumber (int number);
   PageOfEventsBuilder setSize(int size);
   PageOfEventsBuilder setTotalPages(int totalPages);
   PageOfEventsBuilder setTotalElements(int totalElements);
   PageOfEventsBuilder setFirst(boolean first);
   PageOfEventsBuilder setNumberOfElements(int numberOfElements);
   PageOfEventsBuilder setLast(boolean last);
   PageOfEventsBuilder setEvents(List<Event> events);
    PageOfEvents build();
}
