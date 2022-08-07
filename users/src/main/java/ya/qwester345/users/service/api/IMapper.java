package ya.qwester345.users.service.api;

import ya.qwester345.users.dao.entity.UserEntity;
import ya.qwester345.users.dto.ListOfEntity;
import ya.qwester345.users.dto.RegistrationDto;
import ya.qwester345.users.dto.UserCreateDto;
import ya.qwester345.users.dto.UserReadDto;

public interface IMapper {
    UserEntity getUserFromCreateDto(UserCreateDto dto);
    ListOfEntity<UserReadDto> getReadDtoFromEntity(ListOfEntity<UserEntity> all);
    UserReadDto getUserReadDto(UserEntity user);
    UserEntity getUserFromRegistrationDto(RegistrationDto dto);


}
