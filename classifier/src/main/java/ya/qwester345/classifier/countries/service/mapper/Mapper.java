package ya.qwester345.classifier.countries.service.mapper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ya.qwester345.classifier.countries.dao.entity.Category;
import ya.qwester345.classifier.countries.dao.entity.Country;
import ya.qwester345.classifier.countries.dto.CategoryDto;
import ya.qwester345.classifier.countries.dto.CountryDto;
import ya.qwester345.classifier.countries.dto.api.IDto;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@Qualifier("mapper")
public class Mapper  {


    public Country CountryFromDto(CountryDto dto){
        Country country = new Country();
        country.setDescription(dto.getDescription());
        country.setTitle(dto.getTitle());
        country.setDtCreate(LocalDateTime.now());
        country.setDtUpdate(country.getDtCreate());
        country.setUuid(UUID.randomUUID());
        return country;
    }

    public Category entityFromDto(CategoryDto dto){
        Category category = new Category();
        category.setTitle(dto.getTitle());
        category.setDtCreate(LocalDateTime.now());
        category.setDtUpdate(category.getDtCreate());
        category.setUuid(UUID.randomUUID());
        return category;
    }
}
