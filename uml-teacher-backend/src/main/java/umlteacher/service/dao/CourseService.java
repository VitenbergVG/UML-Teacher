package umlteacher.service.dao;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import umlteacher.exceptions.CourseNotFoundException;
import umlteacher.model.dao.Course;
import umlteacher.repo.dao.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;

	public Course findCourseById(int course_id) throws CourseNotFoundException {
		Course course = courseRepository.findById(course_id);
		if (Objects.isNull(course)) {
			throw new CourseNotFoundException(course_id);
		}
		return course;
	}

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	public Course save(Course course) {
		Course c = courseRepository.findById(course.getId());
		if (Objects.isNull(c)) {
			c = new Course();
		}
		c.setName(course.getName());
		c.setDate(course.getDate());
		c.setRating(course.getRating());
		c.setTime(course.getTime());
		return courseRepository.save(c);
	}

	public void delete(int course_id) {
		courseRepository.deleteById(course_id);
	}

}
