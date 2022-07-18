package ya.qwester345.users.service;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ya.qwester345.users.dao.IUserDao;
import ya.qwester345.users.dao.entity.UserEntity;
import ya.qwester345.users.dto.ListOfEntity;
import ya.qwester345.users.dto.UserCreateDto;
import ya.qwester345.users.dto.UserReadDto;
import ya.qwester345.users.service.mapper.Mapper;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class UserService implements UserDetailsManager {
    private IUserDao dao;
    private final Mapper mapper;

    public UserService(IUserDao dao, Mapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }


    public UserEntity create(UserCreateDto dto) {

        return dao.save(mapper.getUserFromCreateDto(dto));
    }


    public ListOfEntity<UserReadDto> getListOfUsers(Pageable pageable) {
        ListOfEntity<UserEntity> userListOfEntity = new ListOfEntity<>(dao.findAll(pageable));
        return mapper.getReadDtoFromEntity(userListOfEntity);
    }


    public UserReadDto getUser(UUID uuid) {
        return mapper.getUserReadDto(dao.findById(uuid).orElseThrow());
    }


    public void update(UUID uuid, LocalDateTime lastKnowDtUpdate, UserCreateDto userCreateDto) {
        UserEntity user = dao.findById(uuid).orElseThrow();
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
}
