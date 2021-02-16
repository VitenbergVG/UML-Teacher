package umlteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umlteacher.exceptions.AuthorizationException;
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
    public User getUser(@RequestHeader("Authorization") String authorizationToken) throws AuthorizationException {
        return userService.getUserByUsernameAndPassword(authorizationToken);
    }

    @PostMapping("/sign-up")
    public User addUser(@RequestHeader("AuthorizationToken") String authorizationToken,
                        @RequestParam("fullname") String fullname) throws AuthorizationException {
        return userService.saveUser(authorizationToken, fullname);
    }
}