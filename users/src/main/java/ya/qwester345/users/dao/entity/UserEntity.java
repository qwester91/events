package ya.qwester345.users.dao.entity;

import org.springframework.security.core.userdetails.UserDetails;
import ya.qwester345.users.dao.entity.enums.Role;
import ya.qwester345.users.dao.entity.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "users_table")
public class UserEntity implements UserDetails {
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Id
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "user_service_schema", name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_mail", referencedColumnName = "email")},
            inverseJoinColumns = { @JoinColumn(name = "role_name", referencedColumnName = "authority")})
    private Set<AuthGrantedAuthority> authorities;

    public UserEntity() {
    }



    public UUID getUuid() {
        return uuid;
    }

    @Override

    public Set<AuthGrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override

    public String getUsername() {
        return this.username;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public Role getRole() {
        return role;
    }




    public Status getStatus() {
        return status;
    }


    public LocalDateTime getDtCreate() {
        return dtCreate;
    }



    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setUsername(String username) {
        this.username = username; }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAuthorities(Set<AuthGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
