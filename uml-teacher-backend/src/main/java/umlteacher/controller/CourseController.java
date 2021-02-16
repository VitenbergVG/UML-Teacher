package umlteacher.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umlteacher.exceptions.CourseNotFoundException;
import umlteacher.model.dao.Course;
import umlteacher.model.dao.User;
import umlteacher.service.dao.CourseService;
import umlteacher.service.dao.UserServiceImpl;

import java.security.Principal;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/get")
    public Object getCourseById(Principal principal,
                                @RequestParam(required = false) Integer courseId,
                                @RequestParam boolean byTeacher) throws CourseNotFoundException {
        User user = (User) userService.loadUserByUsername(principal.getName());
        if (byTeacher) {
            return courseService.findByEmployeeUserId(user.getId());
        }
        if (Objects.isNull(courseId))
            return courseService.findAll();
        Course course = courseService.findCourseById(courseId);
        ObjectNode courseNode = mapper.valueToTree(course);
        courseNode.set("complete", mapper.valueToTree(courseService.getPercent(courseId, user.getId())));
        return courseNode;
    }

    @PostMapping("/save")
    public Course save(@RequestBody Course course) {
        return courseService.save(course);
    }

    @GetMapping("/delete")
    public void delete(@RequestParam int courseId) {
        courseService.delete(courseId);
    }

}
