package ya.qwester345.users.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ya.qwester345.users.dao.IUserDao;
import ya.qwester345.users.dao.entity.User;
import ya.qwester345.users.dto.ListOfEntity;
import ya.qwester345.users.dto.UserCreateDto;
import ya.qwester345.users.service.api.IUserService;
import ya.qwester345.users.service.mapper.Mapper;

import java.util.UUID;
@Service
public class UserService implements IUserService {
    private IUserDao dao;
    private Mapper mapper;

    public UserService(IUserDao dao, Mapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public User create(UserCreateDto dto) {

        return dao.save(mapper.getUserFromCreateDto(dto));
    }

    @Override
    public ListOfEntity<User> getListOfUsers(Pageable pageable) {
        return new ListOfEntity<>(dao.findAll(pageable));
    }

    @Override
    public User getUser(UUID uuid) {
        return dao.findById(uuid).orElseThrow();
    }
}
