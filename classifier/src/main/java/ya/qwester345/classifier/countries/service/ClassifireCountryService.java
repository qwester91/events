package ya.qwester345.classifier.countries.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ya.qwester345.classifier.countries.dao.ICountryDao;
import ya.qwester345.classifier.countries.dao.entity.Country;
import ya.qwester345.classifier.countries.dto.CountryDto;
import ya.qwester345.classifier.countries.dto.ListOfEntity;
import ya.qwester345.classifier.countries.dto.api.IDto;
import ya.qwester345.classifier.countries.service.api.IClassifierService;
import ya.qwester345.classifier.countries.service.mapper.Mapper;

import java.util.UUID;

@Service
public class ClassifireCountryService implements IClassifierService<Country,CountryDto> {
    private ICountryDao dao;
    @Qualifier("mapper")
    private Mapper mapper;
    @Autowired

    public ClassifireCountryService(ICountryDao dao, Mapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public Country add(CountryDto dto) {
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
