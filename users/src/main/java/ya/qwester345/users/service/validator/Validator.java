package ya.qwester345.users.service.validator;


import org.springframework.stereotype.Component;
import ya.qwester345.users.dao.entity.enums.Role;
import ya.qwester345.users.dto.ICreateDto;
import ya.qwester345.users.dto.LoginDto;
import ya.qwester345.users.dto.RegistrationDto;
import ya.qwester345.users.dto.UserCreateDto;
import ya.qwester345.users.service.api.IValidator;
import ya.qwester345.users.service.exeptions.InvalidDtoException;

import java.util.regex.Pattern;
@Component
public class Validator implements IValidator {
    @Override
    public void registrationDtoValidate(RegistrationDto dto) {

        if (dto.getNick()==null){
            throw new InvalidDtoException("nick", "вы не ввели ник");
        }
        validate(dto);
    }

    @Override
    public void userCreateDtoValidate(UserCreateDto dto) {
        if (dto.getRole()==null){
          throw new InvalidDtoException("role", "не определена роль");
        }
        if (!(Role.ADMIN.equals(dto.getRole()) || Role.USER.equals(dto.getRole()))){
            throw new InvalidDtoException("role", "не верно определена роль");
        }
        validate(dto);
    }

    @Override
    public void loginDtoValidate(LoginDto dto) {
       validate(dto);

    }

    private void validate(ICreateDto dto){
        if(!Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
                dto.getEmail())){
            throw new InvalidDtoException("mail", "введен некоректный мэйл");
        }
        if(dto.getPassword().length()<3){
            throw new InvalidDtoException("password", "у тебя слишком короткий... нужно больше 3 символов");
        }
    }
}
