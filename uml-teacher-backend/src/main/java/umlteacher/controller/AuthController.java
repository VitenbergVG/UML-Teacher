package umlteacher.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umlteacher.model.Admin;
import umlteacher.model.User;

@RestController
@RequestMapping("/authorization")
public class AuthController {

    // TODO think about using Spring Security (https://habr.com/ru/post/482552/)

    @PostMapping("/sign-in")
    public User signIn(@RequestBody() String encodedAuthData) {
        // TODO implement this
        return new Admin("Admeeeen");
    }

    @PostMapping("/sign-up")
    public User signUp(@RequestBody() String encodedAuthData) {
        // TODO implement this
        return null;
    }
}
