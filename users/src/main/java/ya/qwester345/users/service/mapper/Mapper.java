package ya.qwester345.users.service.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ya.qwester345.users.dao.entity.Name;
import ya.qwester345.users.dao.entity.UserEntity;
import ya.qwester345.users.dao.entity.enums.Role;
import ya.qwester345.users.dao.entity.enums.Status;
import ya.qwester345.users.dto.ListOfEntity;
import ya.qwester345.users.dto.RegistrationDto;
import ya.qwester345.users.dto.UserCreateDto;
import ya.qwester345.users.dto.UserReadDto;
import ya.qwester345.users.service.api.IMapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class Mapper implements IMapper {

    private final PasswordEncoder encoder;

    public Mapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }
    @Override
    public UserEntity getUserFromCreateDto(UserCreateDto dto) {
        UserEntity user = new UserEntity();
        if (Role.ADMIN.equals(dto.getRole())){
            user.setAuthorities(List.of(new Name("ROLE_ADMIN"),new Name("ROLE_USER")));
        }else{
            user.setAuthorities(List.of(new Name("ROLE_USER")));
        }
        user.setUuid(UUID.randomUUID());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getNick());
        user.setPassword(encoder.encode(dto.getPassword()));
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        user.setDtCreate(localDateTime);
        user.setDtUpdate(localDateTime);
        user.setStatus(dto.getStatus());
        return user;
    }

    @Override
    public ListOfEntity<UserReadDto> getReadDtoFromEntity(ListOfEntity<UserEntity> all) {
        List<UserReadDto> readList = new ArrayList<>();
        ListOfEntity<UserReadDto> newList = new ListOfEntity<>();
        for (UserEntity user : all.getContent()) {
            readList.add(getUserReadDto(user));
        }
        newList.setContent(readList);
        newList.setFirst(all.getFirst());
        newList.setLast(all.getLast());
        newList.setNumber(all.getNumber());
        newList.setSize(all.getSize());
        newList.setTotalElements(all.getTotalElements());
        newList.setNumberOfElements(all.getNumberOfElements());
        newList.setTotalPages(all.getTotalPages());
        return newList;
    }
    @Override
    public UserReadDto getUserReadDto(UserEntity user){
        UserReadDto dto = new UserReadDto();
        dto.setDtCreate(user.getDtCreate());
        dto.setDtUpdate(user.getDtUpdate());
        dto.setEmail(user.getEmail());
        dto.setUuid(user.getUuid());
        dto.setNick(user.getUsername());
        dto.setRole(Role.USER);
        for (Name authority : user.getAuthorities()) {
            if ("ROLE_ADMIN".equals(authority.getAuthority())){
                dto.setRole(Role.ADMIN);
            }
        }
        dto.setStatus(user.getStatus());
        return dto;
    }

    @Override
    public UserEntity getUserFromRegistrationDto(RegistrationDto dto) {
        UserEntity user = new UserEntity();
        Name authority = new Name("ROLE_USER");
        user.setAuthorities(List.of(authority));
        user.setUuid(UUID.randomUUID());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getNick());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setDtCreate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        user.setDtUpdate(user.getDtCreate().truncatedTo(ChronoUnit.MILLIS));
        user.setStatus(Status.WAITING_ACTIVATION);
        return user;
    }
}
