package ya.qwester345.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ya.qwester345.users.dao.IAuthorityDao;
import ya.qwester345.users.dao.IUserDao;
import ya.qwester345.users.dao.entity.UserEntity;
import ya.qwester345.users.dao.entity.enums.Role;
import ya.qwester345.users.dao.entity.enums.Status;
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

    public UserService(IUserDao dao, IAuthorityDao authorityDao, Mapper mapper) {
        this.dao = dao;
        this.authorityDao = authorityDao;
        this.mapper = mapper;
//    UserCreateDto userCreateDto = new UserCreateDto();
//    userCreateDto.setEmail("admin@admin.ru");
//    userCreateDto.setNick("admin");
//    userCreateDto.setPassword("123");
//    userCreateDto.setRole(Role.ADMIN);
//    userCreateDto.setStatus(Status.ACTIVATED);
//
//    this.createUser(mapper.getUserFromCreateDto(userCreateDto));
    }



    @Override
    public void createUser(UserDetails user) {
        dao.saveAndFlush((UserEntity) user);

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
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    public ListOfEntity<UserReadDto> getListOfUsers(Pageable pageable) {
        ListOfEntity<UserEntity> userListOfEntity = new ListOfEntity<>(dao.findAll(pageable));
        return mapper.getReadDtoFromEntity(userListOfEntity);
    }

    public UserReadDto getUser(UUID uuid) {
        return mapper.getUserReadDto(dao.findById(uuid).orElseThrow());
    }

    public UserReadDto getUser() {
        return mapper.getUserReadDto((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }



    @Override
    public void update(UUID uuid, LocalDateTime lastKnowDtUpdate, UserCreateDto userCreateDto) {
        UserEntity user = dao.findById(uuid).orElseThrow();
        LocalDateTime dtCreate = user.getDtCreate();
        if (!user.getDtUpdate().equals(lastKnowDtUpdate)) {
            throw new IllegalStateException("файл был изменен");
        } else {
            user = mapper.getUserFromCreateDto(userCreateDto);
            user.setUuid(uuid);
            user.setDtCreate(dtCreate);
        }
        dao.save(user);

    }
    public UserEntity findUserEntitiesByEmail(String email){
       return dao.findUserEntitiesByEmail(email);
    };
}
