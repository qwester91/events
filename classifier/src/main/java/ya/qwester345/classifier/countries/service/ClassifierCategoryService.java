package ya.qwester345.classifier.countries.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ya.qwester345.classifier.countries.dao.ICategoryDao;
import ya.qwester345.classifier.countries.dao.entity.Category;
import ya.qwester345.classifier.countries.dto.CategoryDto;
import ya.qwester345.classifier.countries.dto.ListOfEntity;
import ya.qwester345.classifier.countries.service.api.IClassifierService;
import ya.qwester345.classifier.countries.service.mapper.Mapper;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Qualifier("category")
public class ClassifierCategoryService implements IClassifierService<Category,CategoryDto> {
    private ICategoryDao dao;
    private Mapper mapper;
    @Autowired
    public ClassifierCategoryService(ICategoryDao dao, Mapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Category add(CategoryDto dto) {
        Category category = dao.save(mapper.categoryFromDto(dto));

        return category;
    }

    @Override
    public ListOfEntity<Category> get(Pageable pageable) {
        return new ListOfEntity<Category>(dao.findAll(pageable));
    }

    @Override
    public boolean isExist(UUID uuid) {
        boolean exists = dao.existsById(uuid);
        return exists;
    }
}
