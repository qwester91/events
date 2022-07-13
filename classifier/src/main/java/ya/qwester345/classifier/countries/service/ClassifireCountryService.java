package ya.qwester345.classifier.countries.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ya.qwester345.classifier.countries.dao.ICountryDao;
import ya.qwester345.classifier.countries.dao.entity.Country;
import ya.qwester345.classifier.countries.dto.ListOfCountries;
import ya.qwester345.classifier.countries.dto.api.IDto;
import ya.qwester345.classifier.countries.service.api.IClassifierService;
import ya.qwester345.classifier.countries.service.api.IMapper;

@Service
public class ClassifireCountryService implements IClassifierService<Country> {
    private ICountryDao dao;
    private IMapper<Country, IDto> mapper;

    public ClassifireCountryService(ICountryDao dao, IMapper<Country, IDto> mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public Country add(IDto dto) {
        Country country = dao.save(mapper.entityFromDto(dto));

        return country;
    }

    @Override
    public ListOfCountries<Country> get(Pageable pageable) {
        return new ListOfCountries<Country>(dao.findAll(pageable));
    }
}
