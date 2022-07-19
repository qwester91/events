package ya.qwester345.users.service.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ya.qwester345.users.dao.entity.UserEntity;
import ya.qwester345.users.dao.entity.enums.Role;
import ya.qwester345.users.dao.entity.enums.Status;
import ya.qwester345.users.dto.ListOfEntity;
import ya.qwester345.users.dto.RegistrationDto;
import ya.qwester345.users.dto.UserCreateDto;
import ya.qwester345.users.dto.UserReadDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Component
public class Mapper {

    private final PasswordEncoder encoder;

    public Mapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public UserEntity getUserFromCreateDto(UserCreateDto dto) {
        UserEntity user = new UserEntity();
        user.setUuid(UUID.randomUUID());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getNick());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setDtCreate(LocalDateTime.now());
        user.setDtUpdate(user.getDtCreate());
        user.setStatus(dto.getStatus());
        user.setRole(dto.getRole());
        return user;
    }

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
    public UserReadDto getUserReadDto(UserEntity user){
        UserReadDto dto = new UserReadDto();
        dto.setDtCreate(user.getDtCreate());
        dto.setDtUpdate(user.getDtUpdate());
        dto.setEmail(user.getEmail());
        dto.setUuid(user.getUuid());
        dto.setNick(user.getUsername());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        return dto;
    }

    public UserEntity getUserFromRegistrationDto(RegistrationDto dto) {
        UserEntity user = new UserEntity();
        user.setUuid(UUID.randomUUID());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getNick());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setDtCreate(LocalDateTime.now());
        user.setDtUpdate(user.getDtCreate());
        user.setStatus(Status.WAITING_ACTIVATION);
        user.setRole(Role.USER);
        return user;
    }
}
