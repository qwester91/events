package ya.qwester345.classifier.service.api;


import org.springframework.data.domain.Pageable;
import ya.qwester345.classifier.dto.ListOfEntity;

import java.util.UUID;


public interface IClassifierService <T,K>{
        T add(K dto);

        ListOfEntity<T> get(Pageable pageable);

    boolean isExist(UUID uuid);
}

