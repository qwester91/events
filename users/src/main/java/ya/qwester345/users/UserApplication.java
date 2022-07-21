package ya.qwester345.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ya.qwester345.users.dao.IAuthorityDao;
import ya.qwester345.users.dao.IUserDao;
import ya.qwester345.users.dao.entity.Name;
import ya.qwester345.users.dao.entity.UserEntity;
import ya.qwester345.users.dao.entity.enums.Role;
import ya.qwester345.users.dao.entity.enums.Status;
import ya.qwester345.users.service.DefaultUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;


@SpringBootApplication
@EnableJpaRepositories("ya/qwester345/users/dao")
public class UserApplication {



    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(UserApplication.class, args);



    }

}
