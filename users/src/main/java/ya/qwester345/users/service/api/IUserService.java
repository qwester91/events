package ya.qwester345.users.service.api;

import org.springframework.data.domain.Pageable;
import ya.qwester345.users.dao.entity.User;
import ya.qwester345.users.dto.ListOfEntity;
import ya.qwester345.users.dto.UserCreateDto;

import java.util.UUID;

public interface IUserService {
    User create(UserCreateDto dto);

    ListOfEntity<User> getListOfUsers(Pageable pageable);

    User getUser(UUID uuid);

}
