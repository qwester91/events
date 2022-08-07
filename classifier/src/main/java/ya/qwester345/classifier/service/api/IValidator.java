package ya.qwester345.classifier.service.api;

import ya.qwester345.classifier.dto.CategoryDto;
import ya.qwester345.classifier.dto.CountryDto;

public interface IValidator {
    void categoryDtoValidate(CategoryDto dto);
    void countryDtoValidate(CountryDto dto);
}
