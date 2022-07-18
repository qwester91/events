package ya.qwester345.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import ya.qwester345.users.dao.IUserDao;
import ya.qwester345.users.dao.entity.AuthGrantedAuthority;
import ya.qwester345.users.dao.entity.UserEntity;
import ya.qwester345.users.dao.entity.enums.Role;
import ya.qwester345.users.service.UserService;
import ya.qwester345.users.service.mapper.Mapper;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class UsersStorageConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public UserDetailsManager userDetailsManager(IUserDao dao, Mapper mapper, PasswordEncoder encoder) {
       UserDetailsManager manager = new UserService( dao, mapper);

        try{
            UserEntity user = new UserEntity() ;
            user.setPassword("123");
            Set<AuthGrantedAuthority> authGrantedAuthoritySet = new HashSet<>();
            AuthGrantedAuthority authGrantedAuthority = new AuthGrantedAuthority();
            authGrantedAuthority.setAuthority(Role.USER.name());

            authGrantedAuthoritySet.add(authGrantedAuthority);

            user.setAuthorities(authGrantedAuthoritySet);
            UserDetails admin = User.builder()
                    .username("admin")
                    .password(encoder.encode("321"))
                    .roles(Role.USER.name(),Role.ADMIN.name())
                    .build();

            manager.createUser(user);
            manager.createUser(admin);
        }catch (DuplicateKeyException e){
            //всё ок, уже есть
        }

        return manager;
    }
}
