package ya.qwester345.users.dao.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "auth_granted_authority")
public class Name implements GrantedAuthority {
    @Id
    private String authority;

    public Name(String authority) {
        this.authority = authority;

    }

    public Name() {
    }

    @Override
    public String getAuthority() {
        return authority;
    }



}

