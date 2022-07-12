package ya.qwester345.events.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class ListOfEvents <T> {
    private Integer number;
    private Integer size;
    private Integer totalPages;
    private Long totalElements;
    private Boolean first;
    private Integer numberOfElements;
    private Boolean last;
    private List<T> content;


    public ListOfEvents(Page<T> page) {
        this.number = page.getNumber();
        this.size = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.first = page.isFirst();
        this.numberOfElements = page.getNumberOfElements();
        this.last = page.isLast();
        this.content = page.getContent();
    }
}
