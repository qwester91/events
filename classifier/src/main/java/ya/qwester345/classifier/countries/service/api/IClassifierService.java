package ya.qwester345.classifier.countries.service.api;

import org.springframework.data.domain.Pageable;
import ya.qwester345.classifier.countries.dto.ListOfCountries;
import ya.qwester345.classifier.countries.dto.api.IDto;

public interface IClassifierService <T>{
    T add(IDto dto);

    ListOfCountries<T> get(Pageable pageable);
}

