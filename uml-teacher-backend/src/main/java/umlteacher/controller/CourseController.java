package umlteacher.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import umlteacher.exceptions.CourseNotFoundException;
import umlteacher.model.dao.Course;
import umlteacher.service.dao.CourseService;

@RestController
@CrossOrigin
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/get")
	public Object getCourseById(@RequestParam(required = false) Integer course_id) throws CourseNotFoundException {
		if (Objects.isNull(course_id))
			return courseService.findAll();
		return courseService.findCourseById(course_id);
	}
	
	@PostMapping("/save")
	public Course save(@RequestBody Course course) {
		return courseService.save(course);
	}
	
	@GetMapping("/delete")
	public void delete(@RequestParam int course_id) {
		courseService.delete(course_id);
	}
	
}
