package umlteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umlteacher.exceptions.AuthorizationException;
import umlteacher.model.dao.Course;
import umlteacher.service.dao.LearningServiceImpl;
import umlteacher.service.dao.UserServiceImpl;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/education")
public class EducationalProcessController {

    private final LearningServiceImpl learningService;
    private final UserServiceImpl userService;

    @Autowired
    public EducationalProcessController(LearningServiceImpl learningService, UserServiceImpl userService) {
        this.learningService = learningService;
        this.userService = userService;
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return learningService.getAllCourses();
    }

    @GetMapping("/courses/current")
    public List<Course> getCurrentCoursesForUser(@RequestHeader("Authorization") String authorizationToken)
            throws AuthorizationException {
        return learningService.getCurrentCoursesForUser(userService.getUserIdByUsernameAndPassword(authorizationToken));
    }
}
