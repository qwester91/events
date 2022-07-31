package ya.qwester345.users.service.api;

import org.springframework.data.domain.Pageable;
import ya.qwester345.users.dao.entity.UserEntity;
import ya.qwester345.users.dto.ListOfEntity;
import ya.qwester345.users.dto.UserCreateDto;
import ya.qwester345.users.dto.UserReadDto;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IUserService {
    UserEntity findUserEntitiesByEmail(String email);

    ListOfEntity<UserReadDto> getListOfUsers(Pageable pageable);

    UserReadDto getUser(UUID uuid);

    void update(UUID uuid, LocalDateTime lastKnowDtUpdate, UserCreateDto userCreateDto);

    UserReadDto getUserFromHolder();
}

