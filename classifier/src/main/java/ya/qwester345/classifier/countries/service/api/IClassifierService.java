package ya.qwester345.classifier.countries.service.api;


import org.springframework.data.domain.Pageable;
import ya.qwester345.classifier.countries.dto.ListOfEntity;

import java.util.UUID;


public interface IClassifierService <T,K>{
        T add(K dto);

        ListOfEntity<T> get(Pageable pageable);

    boolean isExist(UUID uuid);
}

