package ya.qwester345.classifier.service.api;

import ya.qwester345.classifier.dao.entity.Category;
import ya.qwester345.classifier.dao.entity.Country;
import ya.qwester345.classifier.dto.CategoryDto;
import ya.qwester345.classifier.dto.CountryDto;

public interface IMapper {
    Country CountryFromDto(CountryDto dto);
    Category categoryFromDto(CategoryDto dto);
}
