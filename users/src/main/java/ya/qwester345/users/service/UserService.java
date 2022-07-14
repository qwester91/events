package ya.qwester345.users.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ya.qwester345.users.dao.IUserDao;
import ya.qwester345.users.dao.entity.User;
import ya.qwester345.users.dto.ListOfEntity;
import ya.qwester345.users.dto.UserCreateDto;
import ya.qwester345.users.dto.UserReadDto;
import ya.qwester345.users.service.api.IUserService;
import ya.qwester345.users.service.mapper.Mapper;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class UserService implements IUserService {
    private final IUserDao dao;
    private final Mapper mapper;

    public UserService(IUserDao dao, Mapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public User create(UserCreateDto dto) {

        return dao.save(mapper.getUserFromCreateDto(dto));
    }

    @Override
    public ListOfEntity<UserReadDto> getListOfUsers(Pageable pageable) {
        ListOfEntity<User> userListOfEntity = new ListOfEntity<>(dao.findAll(pageable));
        return mapper.getReadDtoFromEntity(userListOfEntity);
    }

    @Override
    public UserReadDto getUser(UUID uuid) {
        return mapper.getUserReadDto(dao.findById(uuid).orElseThrow());
    }

    @Override
    public void update(UUID uuid, LocalDateTime lastKnowDtUpdate, UserCreateDto userCreateDto) {
        User user = dao.findById(uuid).orElseThrow();
        LocalDateTime dtCreate = user.getDtCreate();
        if (!user.getDtUpdate().equals(lastKnowDtUpdate)){
            throw new IllegalStateException("файл был изменен");
        }else {
            user = mapper.getUserFromCreateDto(userCreateDto);
            user.setUuid(uuid);
            user.setDtCreate(dtCreate);
        }
        dao.save(user);

    }
}
