package ya.qwester345.classifier.countries.service.api;


import org.springframework.data.domain.Pageable;
import ya.qwester345.classifier.countries.dto.ListOfEntity;
import ya.qwester345.classifier.countries.dto.api.IDto;



public interface IClassifierService <T>{
        T add(IDto dto);

        ListOfEntity<T> get(Pageable pageable);
    }

