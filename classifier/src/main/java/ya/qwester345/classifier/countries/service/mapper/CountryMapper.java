package ya.qwester345.classifier.countries.service.mapper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ya.qwester345.classifier.countries.dao.entity.Country;
import ya.qwester345.classifier.countries.dto.CountryDto;
import ya.qwester345.classifier.countries.service.api.IMapper;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@Qualifier("mapper")
public class CountryMapper implements IMapper<Country, CountryDto> {

    public Country entityFromDto(CountryDto dto){
        Country country = new Country();
        country.setDescription(dto.getDescription());
        country.setTitle(dto.getTitle());
        country.setDtCreate(LocalDateTime.now());
        country.setDtUpdate(country.getDtCreate());
        country.setUuid(UUID.randomUUID());
        return country;
    }
}
