package ya.qwester345.users.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ya.qwester345.users.dao.entity.AuthGrantedAuthority;

public interface IAuthorityDao extends JpaRepository<AuthGrantedAuthority, Long> {
}
