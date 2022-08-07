package ya.qwester345.users.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ya.qwester345.users.dao.IUserDao;
import ya.qwester345.users.service.api.IUserService;

@Service
public class DetailsService implements UserDetailsService {
    IUserService userService;

    public DetailsService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findUserEntitiesByEmail(username);
    }
}
