package ya.qwester345.users.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ya.qwester345.users.config.utils.JwtTokenUtil;
import ya.qwester345.users.dao.entity.UserEntity;
import ya.qwester345.users.dto.LoginDto;
import ya.qwester345.users.dto.RegistrationDto;
import ya.qwester345.users.dto.UserReadDto;
import ya.qwester345.users.service.UserService;
import ya.qwester345.users.service.api.IMapper;
import ya.qwester345.users.service.api.IUserService;
import ya.qwester345.users.service.api.IValidator;
import ya.qwester345.users.service.mapper.Mapper;

@RestController
@RequestMapping("/api/v1/users")
public class UserLoginController {

    private final IUserService userService;
    private final PasswordEncoder encoder;
    private final IMapper mapper;
    private final IValidator validator;

    public UserLoginController(IUserService userService, PasswordEncoder encoder, IMapper mapper, IValidator validator) {
        this.userService = userService;
        this.encoder = encoder;
        this.mapper = mapper;
        this.validator = validator;
    }

    @PostMapping("/registration")
    public void register(@RequestBody RegistrationDto dto){
        validator.registrationDtoValidate(dto);
        UserEntity user = mapper.getUserFromRegistrationDto(dto);
        userService.createUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto){
        validator.loginDtoValidate(loginDto);
        UserEntity user = userService.findUserEntitiesByEmail(loginDto.getEmail());

        if(!encoder.matches(loginDto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Пароль неверный");
        }
        return JwtTokenUtil.generateAccessToken(user);
    }

    @GetMapping("/me")
    public UserReadDto getMe(@RequestHeader (name = "Authorization") String token){
      return userService.getUserFromHolder();
    }
}
