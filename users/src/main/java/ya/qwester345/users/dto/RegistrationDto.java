package ya.qwester345.users.dto;

public class RegistrationDto {

    private String email;
    private String password;
    private String nick;

    public RegistrationDto() {
    }

    public RegistrationDto(String email, String password, String nick) {
        this.email = email;
        this.password = password;
        this.nick = nick;
    }

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
}
