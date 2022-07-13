package ya.qwester345.classifier.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ya.qwester345.classifier.category.dao.ICategoryDao;
import ya.qwester345.classifier.category.dao.entity.Category;
import ya.qwester345.classifier.countries.dto.ListOfEntity;
import ya.qwester345.classifier.countries.dto.api.IDto;
import ya.qwester345.classifier.countries.service.api.IClassifierService;
import ya.qwester345.classifier.countries.service.api.IMapper;

@Service
@Qualifier("category")
public class ClassifireCategoryService implements IClassifierService<Category> {
    private ICategoryDao dao;
    @Qualifier("categoryMapper")
    private IMapper<Category, IDto> mapper;
    @Autowired
    public ClassifireCategoryService(ICategoryDao dao, IMapper<Category, IDto> mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public Category add(IDto dto) {
        Category category = dao.save(mapper.entityFromDto(dto));

        return category;
    }

    @Override
    public ListOfEntity<Category> get(Pageable pageable) {
        return new ListOfEntity<Category>(dao.findAll(pageable));
    }
}
