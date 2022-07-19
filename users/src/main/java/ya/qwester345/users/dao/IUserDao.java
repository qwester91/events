package ya.qwester345.users.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ya.qwester345.users.dao.entity.UserEntity;

import java.util.UUID;
@Repository
public interface IUserDao extends JpaRepository<UserEntity,UUID> {

    Page<UserEntity> findAll(Pageable pageable);
    UserEntity findUserEntitiesByEmail(String username);
}
