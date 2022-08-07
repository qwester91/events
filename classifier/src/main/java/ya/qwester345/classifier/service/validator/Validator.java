package ya.qwester345.classifier.service.validator;

import ya.qwester345.classifier.dto.CategoryDto;
import ya.qwester345.classifier.dto.CountryDto;
import ya.qwester345.classifier.service.api.IValidator;
import ya.qwester345.classifier.service.exeptions.InvalidDtoException;

public class Validator implements IValidator {

    public void categoryDtoValidate(CategoryDto dto){
        if(dto.getTitle()==null){
            throw new InvalidDtoException("title", "ну и шо ты ничего не добавил?");
        }
    }

    public void countryDtoValidate(CountryDto dto){
        if(dto.getTitle()==null){
            throw new InvalidDtoException("title", "ну и шо ты ничего не добавил?");
        }
        if(dto.getDescription()==null){
            throw new InvalidDtoException("description", "Добавь описание, лентяй!!!");
        }
    }
}
