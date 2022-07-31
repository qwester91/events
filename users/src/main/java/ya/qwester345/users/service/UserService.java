package ya.qwester345.users.service;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ya.qwester345.users.dao.IAuthorityDao;
import ya.qwester345.users.dao.IUserDao;
import ya.qwester345.users.dao.entity.UserEntity;
import ya.qwester345.users.dto.ListOfEntity;
import ya.qwester345.users.dto.UserCreateDto;
import ya.qwester345.users.dto.UserReadDto;
import ya.qwester345.users.service.api.IUserService;
import ya.qwester345.users.service.mapper.Mapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {

    private final IUserDao dao;
    private final IAuthorityDao authorityDao;
    private final Mapper mapper;

    public UserService(IUserDao dao, IAuthorityDao authorityDao, Mapper mapper) {
        this.dao = dao;
        this.authorityDao = authorityDao;
        this.mapper = mapper;
    }


    @Transactional
    public void createUser(UserDetails user) {
        dao.save((UserEntity) user);

    }

    @Override
    public ListOfEntity<UserReadDto> getListOfUsers(Pageable pageable) {
        ListOfEntity<UserEntity> userListOfEntity = new ListOfEntity<>(dao.findAll(pageable));
        return mapper.getReadDtoFromEntity(userListOfEntity);
    }
    @Override
    public UserReadDto getUser(UUID uuid) {
        return mapper.getUserReadDto(dao.findById(uuid).orElseThrow());
    }

    @Override
    public UserReadDto getUserFromHolder() {
        return mapper.getUserReadDto((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }


    @Transactional
    @Override
    public void update(UUID uuid, LocalDateTime lastKnowDtUpdate, UserCreateDto userCreateDto) {
        UserEntity user = dao.findById(uuid).orElseThrow();
        LocalDateTime dtCreate = user.getDtCreate();
        if (!user.getDtUpdate().truncatedTo(ChronoUnit.MILLIS).equals(lastKnowDtUpdate)) {
            throw new IllegalStateException("файл был изменен");
        } else {
            user = mapper.getUserFromCreateDto(userCreateDto);
            user.setUuid(uuid);
            user.setDtCreate(dtCreate);
        }
        dao.save(user);

    }
    @Override
    public UserEntity findUserEntitiesByEmail(String email){
       return dao.findUserEntitiesByEmail(email);
    };
}
