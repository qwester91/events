package ya.qwester345.classifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ya.qwester345.classifier.dao.ICategoryDao;
import ya.qwester345.classifier.dao.entity.Category;
import ya.qwester345.classifier.dto.CategoryDto;
import ya.qwester345.classifier.dto.ListOfEntity;
import ya.qwester345.classifier.service.api.IClassifierService;
import ya.qwester345.classifier.service.api.IMapper;
import ya.qwester345.classifier.service.api.IValidator;
import ya.qwester345.classifier.service.mapper.Mapper;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Qualifier("category")
public class ClassifierCategoryService implements IClassifierService<Category,CategoryDto> {
    private ICategoryDao dao;
    private IMapper mapper;
    private IValidator validator;

    public ClassifierCategoryService(ICategoryDao dao, IMapper mapper, IValidator validator) {
        this.dao = dao;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Autowired


    @Override
    @Transactional
    public Category add(CategoryDto dto) {
        validator.categoryDtoValidate(dto);
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
