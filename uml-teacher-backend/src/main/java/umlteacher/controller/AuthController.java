package umlteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import umlteacher.model.dao.User;
import umlteacher.service.dao.UserServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/authorization")
public class AuthController {

    private final UserServiceImpl userService;

    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-in")
    public UserDetails getUser(String username, String password) {
        return userService.loadUserByUsernameAndPassword(username, password);
    }

    @PostMapping("/sign-up")
    public boolean addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
