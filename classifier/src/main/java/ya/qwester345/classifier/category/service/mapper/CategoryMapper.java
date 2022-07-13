package ya.qwester345.classifier.category.service.mapper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ya.qwester345.classifier.category.dao.entity.Category;
import ya.qwester345.classifier.category.dto.CategoryDto;

import ya.qwester345.classifier.countries.service.api.IMapper;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@Qualifier("categoryMapper")
public class CategoryMapper implements IMapper<Category, CategoryDto> {

    public Category entityFromDto(CategoryDto dto){
        Category category = new Category();
        category.setTitle(dto.getTitle());
        category.setDtCreate(LocalDateTime.now());
        category.setDtUpdate(category.getDtCreate());
        category.setUuid(UUID.randomUUID());
        return category;
    }
}
