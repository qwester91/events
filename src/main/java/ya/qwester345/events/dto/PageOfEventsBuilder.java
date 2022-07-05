package ya.qwester345.events.dto;

import ya.qwester345.events.dao.entity.Event;
import ya.qwester345.events.dto.api.IPageOfEventsBuilder;

import java.util.List;

public class PageOfEventsBuilder implements IPageOfEventsBuilder {

    private PageOfEvents pageOfEvents;

    public PageOfEventsBuilder() {
        pageOfEvents = new PageOfEvents();
    }

    @Override
    public void setNumber(int number) {
        pageOfEvents.setNumber(number);
    }

    @Override
    public void setSize(int size) {
        pageOfEvents.setSize(size);
    }

    @Override
    public void setTotalPages(int totalPages) {
        pageOfEvents.setTotalPages(totalPages);
    }

    @Override
    public void setTotalElements(int totalElements) {
        pageOfEvents.setTotalElements(totalElements);
    }

    @Override
    public void setFirst(boolean first) {
        pageOfEvents.setFirst(first);
    }

    @Override
    public void setNumberOfElements(int numberOfElements) {
        pageOfEvents.setNumberOfElements(numberOfElements);
    }

    @Override
    public void setLast(boolean last) {
        pageOfEvents.setLast(last);
    }

    @Override
    public void setEvents(List<Event> events) {
        pageOfEvents.setEvents(events);
    }

    @Override
    public PageOfEvents build() {
        return this.pageOfEvents;
    }
}
