package ya.qwester345.classifier.category.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ya.qwester345.classifier.category.dao.entity.Category;

import java.util.UUID;

public interface ICategoryDao extends JpaRepository<Category, UUID> {
    Page<Category> findAll(Pageable pageable);
}
