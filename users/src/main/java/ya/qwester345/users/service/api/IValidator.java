package ya.qwester345.users.service.api;

import ya.qwester345.users.dto.LoginDto;
import ya.qwester345.users.dto.RegistrationDto;
import ya.qwester345.users.dto.UserCreateDto;

public interface IValidator {
    void registrationDtoValidate(RegistrationDto dto);
    void userCreateDtoValidate(UserCreateDto dto);
    void loginDtoValidate(LoginDto dto);
}
