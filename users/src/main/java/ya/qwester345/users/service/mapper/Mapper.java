package ya.qwester345.users.service.mapper;

import org.springframework.stereotype.Component;
import ya.qwester345.users.dao.entity.User;
import ya.qwester345.users.dto.UserCreateDto;

import java.time.LocalDateTime;
import java.util.UUID;
@Component
public class Mapper {
    public User getUserFromCreateDto(UserCreateDto dto) {
        User user = new User();
        user.setUuid(UUID.randomUUID());
        user.setEmail(dto.getEmail());
        user.setNick(dto.getNick());
        user.setPassword(dto.getPassword());
        user.setDtCreate(LocalDateTime.now());
        user.setDtUpdate(user.getDtCreate());
        user.setStatus(dto.getStatus());
        user.setRole(dto.getRole());
        return user;
    }
}
