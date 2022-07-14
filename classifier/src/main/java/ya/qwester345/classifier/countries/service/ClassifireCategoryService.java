package ya.qwester345.classifier.countries.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ya.qwester345.classifier.countries.dao.ICategoryDao;
import ya.qwester345.classifier.countries.dao.entity.Category;
import ya.qwester345.classifier.countries.dto.CategoryDto;
import ya.qwester345.classifier.countries.dto.ListOfEntity;
import ya.qwester345.classifier.countries.dto.api.IDto;
import ya.qwester345.classifier.countries.service.api.IClassifierService;
import ya.qwester345.classifier.countries.service.mapper.Mapper;

@Service
@Qualifier("category")
public class ClassifireCategoryService implements IClassifierService<Category,CategoryDto> {
    private ICategoryDao dao;
    @Qualifier("categoryMapper")
    private Mapper mapper;
    @Autowired
    public ClassifireCategoryService(ICategoryDao dao, Mapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public Category add(CategoryDto dto) {
        Category category = dao.save(mapper.entityFromDto(dto));

        return category;
    }

    @Override
    public ListOfEntity<Category> get(Pageable pageable) {
        return new ListOfEntity<Category>(dao.findAll(pageable));
    }
}
