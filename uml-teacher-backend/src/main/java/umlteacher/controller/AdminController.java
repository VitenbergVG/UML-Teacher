package umlteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umlteacher.model.dao.User;
import umlteacher.service.dao.UserServiceImpl;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    public List<User> getUsersList() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{userId}")
    public boolean deleteUser(@PathVariable("userId") int userId) {
        return userService.deleteUser(userId);
    }
}
