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
    public PageOfEventsBuilder setNumber(int number) {
        pageOfEvents.setNumber(number);
        return this;
    }

    @Override
    public PageOfEventsBuilder setSize(int size) {
        pageOfEvents.setSize(size);
        return this;
    }

    @Override
    public PageOfEventsBuilder setTotalPages(int totalPages) {
        pageOfEvents.setTotalPages(totalPages);
        return this;
    }

    @Override
    public PageOfEventsBuilder setTotalElements(int totalElements) {
        pageOfEvents.setTotalElements(totalElements);
        return this;
    }

    @Override
    public PageOfEventsBuilder setFirst(boolean first) {
        pageOfEvents.setFirst(first);
        return this;
    }

    @Override
    public PageOfEventsBuilder setNumberOfElements(int numberOfElements) {
        pageOfEvents.setNumberOfElements(numberOfElements);
        return this;
    }

    @Override
    public PageOfEventsBuilder setLast(boolean last) {
        pageOfEvents.setLast(last);
        return this;
    }

    @Override
    public PageOfEventsBuilder setEvents(List<Event> events) {
        pageOfEvents.setEvents(events);
        return this;
    }

    @Override
    public PageOfEvents build() {
        return this.pageOfEvents;
    }
}
