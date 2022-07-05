package ya.qwester345.events.dto;

import ya.qwester345.events.dao.entity.Event;
import java.util.List;

public class PageOfEvents {
   private Integer number;
   private Integer size;
   private Integer totalPages;
   private Integer totalElements;
   private Boolean first;
   private Integer numberOfElements;
   private Boolean last;
   private List <Event> events;

    public PageOfEvents() {
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
