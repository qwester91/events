package ya.qwester345.classifier.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@JsonDeserialize(builder = UserDto.Builder.class)
public class UserDto implements UserDetails {

    private final String email;

    private final Set<GrantedAuthority> authorities;

    private final boolean isEnable;

    public UserDto(String email,
                   Set<GrantedAuthority> authorities,
                   boolean isEnable) {
        this.email = email;
        this.authorities = authorities;
        this.isEnable = isEnable;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public boolean isEnable() {
        return isEnable;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static Builder create() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private Builder() {}

        private String email;

        private Set<GrantedAuthority> authorities;

        private boolean isEnable;

        @JsonSetter("email")
        public void setUsername(String email) {
            this.email = email;
        }

        @JsonSetter("role")
        public void setAuthorities(String authorities) {
            this.authorities = Arrays.stream(
                            authorities.replace(", ", " ")
                                    .split(" ")
                    ).map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }

        @JsonSetter("status")
        public void setEnable(String status) {
            isEnable = "ACTIVATED".equals(status);
        }

        public UserDto build() {
            return new UserDto(email, authorities, isEnable);
        }
    }
}
