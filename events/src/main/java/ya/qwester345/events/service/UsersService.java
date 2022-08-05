package ya.qwester345.events.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ya.qwester345.events.dto.UserDto;
import ya.qwester345.events.service.api.IUserService;

import java.util.Objects;
@Component
public class UsersService implements IUserService {
    @Override
    public UserDto getUser(){
         UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (Objects.equals("anonymousUser", user)) {
            user = null;
        }
        return user;
    }
}
