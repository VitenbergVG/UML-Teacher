package umlteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import umlteacher.exceptions.AuthorizationException;
import umlteacher.model.dao.Answer;
import umlteacher.model.dao.Course;
import umlteacher.model.dao.User;
import umlteacher.service.dao.AnswerService;
import umlteacher.service.dao.CourseService;
import umlteacher.service.dao.LearningServiceImpl;
import umlteacher.service.dao.UserServiceImpl;

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

    @Autowired
    public EducationalProcessController(LearningServiceImpl learningService,
    									UserServiceImpl userService,
    									AnswerService answerService,
    									ObjectMapper mapper,
    									CourseService courseService) {
        this.learningService = learningService;
        this.userService = userService;
        this.answerService = answerService;
        this.mapper = mapper;
        this.courseService = courseService;
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
    		ObjectNode course = mapper.valueToTree(c);
    		course.set("complete", mapper.valueToTree(courseService.getPercent(c.getId(), user.getId())));
    		response.add(course);
    	}
    	return response;
    }
    
    @GetMapping("/courses/completed")
    public Set<Course> getCompleted(Principal principal) {
    	User user = (User) userService.loadUserByUsername(principal.getName());
    	return courseService.getCompleted(user.getId());
    }
    
    @PostMapping("/answers/add")
    public Answer addAnswer(@RequestParam int course_id,
    						@RequestParam(required = false) Integer task_id,
    						@RequestParam(required = false)	Byte task_number,
    						@RequestBody String answer,
    						Principal principal) {
    	
    	User user = (User) userService.loadUserByUsername(principal.getName());
    	if (Objects.nonNull(task_id)) {
    		return answerService.addAnswer(user.getId(), course_id, task_id, answer);    		
    	}
    	else if (Objects.nonNull(task_number)) {
    		return answerService.addAnswer(user.getId(), course_id, task_number, answer);
    	}
    	else
    		throw new RuntimeException("task_id or task_number required");
    }
    
    @GetMapping("/answers/get")
    public Object get(@RequestParam int course_id, @RequestParam(required = false) Boolean unchecked, @RequestParam(required = false) Integer task_id) {
    	if (Objects.isNull(task_id)) {
    		return answerService.getByCourseId(course_id, unchecked);
    	}
    	return answerService.getByCourseIdAndTaskId(course_id, task_id);
    }
    
    @PostMapping("answers/check")
    public void check(@RequestParam int answer_id, @RequestBody boolean isCorrect, Principal principal) {
    	User user = (User) userService.loadUserByUsername(principal.getName());
    	answerService.check(answer_id, user.getId(), isCorrect);
    }
}
