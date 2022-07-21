package ya.qwester345.users.dao.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    Role() {
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}
