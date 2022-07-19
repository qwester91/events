package ya.qwester345.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ya.qwester345.users.dao.IAuthorityDao;
import ya.qwester345.users.dao.IUserDao;
import ya.qwester345.users.dao.entity.UserEntity;
import ya.qwester345.users.dto.ListOfEntity;
import ya.qwester345.users.dto.UserCreateDto;
import ya.qwester345.users.dto.UserReadDto;
import ya.qwester345.users.service.api.IUserService;
import ya.qwester345.users.service.mapper.Mapper;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class UserService implements UserDetailsManager , IUserService {

    private final IUserDao dao;
    private final IAuthorityDao authorityDao;
    private final Mapper mapper;
@Autowired
    public UserService(IUserDao dao, IAuthorityDao authorityDao, Mapper mapper) {
        this.dao = dao;
        this.authorityDao = authorityDao;
        this.mapper = mapper;
    }



    @Override
    public void createUser(UserDetails user) {
        dao.save((UserEntity) user);

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public ListOfEntity<UserReadDto> getListOfUsers(Pageable pageable) {
        ListOfEntity<UserEntity> userListOfEntity = new ListOfEntity<>(dao.findAll(pageable));
        return mapper.getReadDtoFromEntity(userListOfEntity);
    }

    public UserReadDto getUser(UUID uuid) {
        return mapper.getUserReadDto(dao.findById(uuid).orElseThrow());
    }

    @Override
    public void update(UUID uuid, LocalDateTime lastKnowDtUpdate, UserCreateDto userCreateDto) {

    }


}
