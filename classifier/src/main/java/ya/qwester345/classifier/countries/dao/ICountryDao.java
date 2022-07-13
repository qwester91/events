package ya.qwester345.classifier.countries.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ya.qwester345.classifier.countries.dao.entity.Country;

import java.util.List;
import java.util.UUID;

public interface ICountryDao extends JpaRepository<Country, UUID> {
    Page<Country> findAll(Pageable pageable);
}
