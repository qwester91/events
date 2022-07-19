package ya.qwester345.users.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ya.qwester345.users.dao.entity.AuthGrantedAuthority;
@Repository
public interface IAuthorityDao extends JpaRepository<AuthGrantedAuthority, Long> {
}
