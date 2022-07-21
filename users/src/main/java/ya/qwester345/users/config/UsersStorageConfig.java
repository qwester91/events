package ya.qwester345.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UsersStorageConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserService userService(IAuthorityDao authorityDao, IUserDao dao, Mapper mapper) {
//       UserService manager = new UserService( dao,authorityDao, mapper);
//       AuthGrantedAuthority authGrantedAuthority = new AuthGrantedAuthority();
//        authGrantedAuthority.setAuthority(Role.USER.name());
//       authorityDao.save(authGrantedAuthority);
//
//        try{
//            UserCreateDto userCreateDto = new UserCreateDto();
//            userCreateDto.setEmail("admin@admin.ru");
//            userCreateDto.setNick("admin");
//            userCreateDto.setPassword("123");
//            userCreateDto.setRole(Role.ADMIN);
//            userCreateDto.setStatus(Status.ACTIVATED);
//
//            manager.createUser(mapper.getUserFromCreateDto(userCreateDto));
//
//
//        }catch (DuplicateKeyException e){
//            //всё ок, уже есть
//        }
//
//        return manager;
//    }
}
