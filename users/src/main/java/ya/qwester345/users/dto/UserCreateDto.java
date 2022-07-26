package ya.qwester345.users.dto;

import ya.qwester345.users.dao.entity.Name;
import ya.qwester345.users.dao.entity.enums.Role;
import ya.qwester345.users.dao.entity.enums.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserCreateDto {
    private String email;
    private String password;
    private String nick;
    private Role role;
    private Status status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
