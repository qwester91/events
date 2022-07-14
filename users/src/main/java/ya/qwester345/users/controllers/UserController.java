package ya.qwester345.users.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ya.qwester345.users.dao.entity.User;
import ya.qwester345.users.dto.ListOfEntity;
import ya.qwester345.users.dto.UserCreateDto;
import ya.qwester345.users.dto.UserReadDto;
import ya.qwester345.users.service.api.IUserService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private IUserService userService;
    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody UserCreateDto dto){
        return new ResponseEntity<>(userService.create(dto), HttpStatus.CREATED);
    }
    @GetMapping
    public ListOfEntity<UserReadDto> getListOfUsers(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                    @RequestParam(value = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page-1, size);
        return this.userService.getListOfUsers(pageable);
    }

    @GetMapping("/{uuid}")
    public UserReadDto getUserByUuid(@PathVariable UUID uuid){
        return this.userService.getUser(uuid);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public void updateEvent(@PathVariable(name = "uuid") UUID uuid,
                            @PathVariable(name = "dt_update") Long dtUpdate,
                            @RequestBody UserCreateDto userCreateDto){
        LocalDateTime lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());
        this.userService.update( uuid, lastKnowDtUpdate, userCreateDto);
    }

    
}
