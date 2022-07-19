package ya.qwester345.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ya.qwester345.users.dao.IAuthorityDao;
import ya.qwester345.users.dao.IUserDao;


@SpringBootApplication
@EnableJpaRepositories
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);


    }

}
