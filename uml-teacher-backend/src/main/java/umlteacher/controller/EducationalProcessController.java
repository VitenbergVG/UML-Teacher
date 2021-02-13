package umlteacher.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umlteacher.exceptions.AuthorizationException;
import umlteacher.model.dao.Answer;
import umlteacher.model.dao.Course;
import umlteacher.model.dao.Group;
import umlteacher.model.dao.User;
import umlteacher.service.dao.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/education")
public class EducationalProcessController {

    private final LearningServiceImpl learningService;
    private final UserServiceImpl userService;
    private final AnswerService answerService;
    private final ObjectMapper mapper;
    private final CourseService courseService;
    private final GroupService groupService;

    @Autowired
    public EducationalProcessController(LearningServiceImpl learningService,
                                        UserServiceImpl userService,
                                        AnswerService answerService,
                                        ObjectMapper mapper,
                                        CourseService courseService, GroupService groupService) {
        this.learningService = learningService;
        this.userService = userService;
        this.answerService = answerService;
        this.mapper = mapper;
        this.courseService = courseService;
        this.groupService = groupService;
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return learningService.getAllCourses();
    }

    @GetMapping("/courses/current")
    public Object getCurrentCoursesForUser(Principal principal)
            throws AuthorizationException {
        User user = (User) userService.loadUserByUsername(principal.getName());
        List<Course> courses = learningService.getCurrentCoursesForUser(user.getId());
        ArrayNode response = mapper.createArrayNode();
        for (Course c : courses) {
            Double percent = courseService.getPercent(c.getId(), user.getId());
            ObjectNode course = mapper.valueToTree(c);
            if (!percent.equals(100.0)) {
                course.set("complete", mapper.valueToTree(percent));
                response.add(course);
            }
        }
        return response;
    }

    @GetMapping("/courses/completed")
    public Set<Course> getCompleted(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        return courseService.getCompleted(user.getId());
    }

    @PostMapping("/answers/add")
    public Answer addAnswer(@RequestParam int courseId,
                            @RequestParam(required = false) Integer taskId,
                            @RequestParam(required = false) Byte taskNumber,
                            @RequestParam(required = false) Boolean isCorrect,
                            @RequestBody String answer,
                            Principal principal) {

        User user = (User) userService.loadUserByUsername(principal.getName());
        if (Objects.nonNull(taskId)) {
            return answerService.addAnswer(user.getId(), courseId, taskId, answer, isCorrect);
        } else if (Objects.nonNull(taskNumber)) {
            return answerService.addAnswer(user.getId(), courseId, taskNumber, answer, isCorrect);
        } else
            throw new RuntimeException("taskId or taskNumber required");
    }

    @GetMapping("/answers/get")
    public Object get(@RequestParam int courseId,
                      @RequestParam(required = false) Boolean unchecked,
                      @RequestParam(required = false) Byte taskNumber) {
        if (Objects.isNull(taskNumber)) {
            return answerService.getByCourseId(courseId, unchecked);
        }
        return answerService.getByCourseIdAndTaskNumber(courseId, taskNumber);
    }

    @PostMapping("/answers/check")
    public void check(@RequestParam int answerId, @RequestBody boolean isCorrect, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        answerService.check(answerId, user.getId(), isCorrect);
    }

    @GetMapping("/courses/join")
    public void joinToCourse(@RequestParam("courseId") int courseId,
                             Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        Group group = groupService.findGroupByCourseId(courseId);
        groupService.join(group.getId(), user.getId());
    }
}
