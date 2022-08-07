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
        final UserDto user;
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!Objects.equals("anonymousUser", principal)) {
            user = (UserDto) principal;

        } else {
            user = null;
        }

        return user;
    }
}
