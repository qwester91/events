package ya.qwester345.users.controllers;

import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ya.qwester345.users.config.utils.JwtTokenUtil;
import ya.qwester345.users.dao.entity.UserEntity;
import ya.qwester345.users.dto.LoginDto;
import ya.qwester345.users.dto.RegistrationDto;
import ya.qwester345.users.dto.UserReadDto;
import ya.qwester345.users.service.UserService;
import ya.qwester345.users.service.mapper.Mapper;

@RestController
@RequestMapping("/api/v1/users")
public class UserLoginController {

    private final UserService userService;
    private final PasswordEncoder encoder;
    private final Mapper mapper;

    public UserLoginController(UserService userService, PasswordEncoder encoder, Mapper mapper) {
        this.userService = userService;
        this.encoder = encoder;
        this.mapper = mapper;
    }

    @PostMapping("/registration")
    public void register(@RequestBody RegistrationDto dto){
        UserEntity user = mapper.getUserFromRegistrationDto(dto);
        userService.createUser(user);

    }

    @PostMapping("/login")

    public String login(@RequestBody LoginDto loginDto){
        UserEntity user = userService.findUserEntitiesByEmail(loginDto.getEmail());

        if(!encoder.matches(loginDto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Пароль неверный");
        }

        return JwtTokenUtil.generateAccessToken(user);
    }

    @GetMapping("/me")
    public UserReadDto getMe(@RequestHeader (name = "Authorization") String token){


      return userService.getUser();

    }



}
