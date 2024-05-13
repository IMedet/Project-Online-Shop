package kz.medet.onlineshop.api;

import kz.medet.onlineshop.dto.UserDto;
import kz.medet.onlineshop.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserControllerApi {
    private final UserServiceImpl userService;

    @GetMapping(value = "/users")
    public List<UserDto> getAllUsersApi(){
        return userService.getAllUsers();
    }

//    @PostMapping(value = "/sign")
}
