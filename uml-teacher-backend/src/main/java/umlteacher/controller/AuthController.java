package umlteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umlteacher.exceptions.AuthorizationException;
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
    public Long getUser(@RequestHeader("Authorization") String authorizationToken) throws AuthorizationException {
        return userService.getUserIdByUsernameAndPassword(authorizationToken);
    }

    @PostMapping("/sign-up")
    public Long addUser(@RequestHeader("AuthorizationToken") String authorizationToken,
                        @RequestParam("fullname") String fullname) throws AuthorizationException {
        return userService.saveUserAndGetId(authorizationToken, fullname);
    }

    @GetMapping({"/has-admin-role"})
    public boolean userHasAdminRole(@RequestParam("userId") Long userId) throws AuthorizationException {
        return userService.isAdmin(userId);
    }
}