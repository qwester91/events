package ya.qwester345.users.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ya.qwester345.users.dao.IUserDao;

@Service
public class DetailsService implements UserDetailsService {
    IUserDao dao;

    public DetailsService(IUserDao dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dao.findUserEntitiesByEmail(username);
    }
}
