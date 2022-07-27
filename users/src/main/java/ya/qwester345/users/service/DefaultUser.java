package ya.qwester345.users.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ya.qwester345.users.dao.IAuthorityDao;
import ya.qwester345.users.dao.IUserDao;
import ya.qwester345.users.dao.entity.Name;
import ya.qwester345.users.dao.entity.UserEntity;
import ya.qwester345.users.dao.entity.enums.Role;
import ya.qwester345.users.dao.entity.enums.Status;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Component
public class DefaultUser implements CommandLineRunner {


    private final IUserDao dao;
    private final IAuthorityDao authorityDao;
    private final PasswordEncoder encoder;

    public DefaultUser(IUserDao dao, IAuthorityDao authorityDao, PasswordEncoder encoder) {
        this.dao = dao;
        this.authorityDao = authorityDao;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
    try{
        Name authorities = new Name("ROLE_ADMIN");
        authorityDao.save(authorities);
        Name authorities1 = new Name("ROLE_USER");
        authorityDao.save(authorities1);


            UserEntity admin = new UserEntity();
            admin.setUuid(UUID.randomUUID());
            admin.setDtCreate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
            admin.setDtUpdate(admin.getDtCreate());
            admin.setEmail("admin@gmail.com");
            admin.setUsername("admin");
            admin.setStatus(Status.ACTIVATED);
            admin.setPassword(encoder.encode("admin"));
            Name roleAdmin = new Name("ROLE_ADMIN");

            admin.setAuthorities(List.of(roleAdmin));


            dao.save(admin);}
    catch (Exception e){}

    }
}
