package ya.qwester345.classifier.service.mapper;

import org.springframework.stereotype.Service;
import ya.qwester345.classifier.dao.entity.Category;
import ya.qwester345.classifier.dao.entity.Country;
import ya.qwester345.classifier.dto.CategoryDto;
import ya.qwester345.classifier.dto.CountryDto;
import ya.qwester345.classifier.service.api.IMapper;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class Mapper implements IMapper {


    public Country CountryFromDto(CountryDto dto){
        Country country = new Country();
        country.setDescription(dto.getDescription());
        country.setTitle(dto.getTitle());
        country.setDtCreate(LocalDateTime.now());
        country.setDtUpdate(country.getDtCreate());
        country.setUuid(UUID.randomUUID());
        return country;
    }

    public Category categoryFromDto(CategoryDto dto){
        Category category = new Category();
        category.setTitle(dto.getTitle());
        category.setDtCreate(LocalDateTime.now());
        category.setDtUpdate(category.getDtCreate());
        category.setUuid(UUID.randomUUID());
        return category;
    }
}
