package ya.qwester345.classifier.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ya.qwester345.classifier.dao.ICountryDao;
import ya.qwester345.classifier.dao.entity.Country;
import ya.qwester345.classifier.dto.CountryDto;
import ya.qwester345.classifier.dto.ListOfEntity;
import ya.qwester345.classifier.service.api.IClassifierService;
import ya.qwester345.classifier.service.api.IMapper;
import ya.qwester345.classifier.service.api.IValidator;
import ya.qwester345.classifier.service.mapper.Mapper;

import java.util.UUID;

@Service
public class ClassifierCountryService implements IClassifierService<Country,CountryDto> {
    private ICountryDao dao;
    private IMapper mapper;
    private IValidator validator;

    public ClassifierCountryService(ICountryDao dao, IMapper mapper, IValidator validator) {
        this.dao = dao;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public Country add(CountryDto dto) {
        validator.countryDtoValidate(dto);
        Country country = dao.save(mapper.CountryFromDto(dto));

        return country;
    }

    @Override
    public ListOfEntity<Country> get(Pageable pageable) {
        return new ListOfEntity<Country>(dao.findAll(pageable));
    }

    @Override
    public boolean isExist(UUID uuid) {
        boolean exists = dao.existsById(uuid);
        return exists;
    }
}
