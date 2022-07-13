package ya.qwester345.classifier.category.dto;

import ya.qwester345.classifier.countries.dto.api.IDto;

public class CategoryDto implements IDto {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
