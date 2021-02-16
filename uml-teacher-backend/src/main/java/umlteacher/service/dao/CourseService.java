package umlteacher.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umlteacher.exceptions.CourseNotFoundException;
import umlteacher.exceptions.StudentNotFoundException;
import umlteacher.model.dao.Course;
import umlteacher.model.dao.Employee;
import umlteacher.model.dao.Student;
import umlteacher.repo.dao.CourseRepository;
import umlteacher.repo.dao.EmployeeRepository;
import umlteacher.repo.dao.StudentRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

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

	public List<Course> findByEmployeeUserId(long id) {
		Employee employee = employeeRepository.findByUserId(id);
		return courseRepository.findByEmployeeId(employee.getId());
	}

	public Set<Course> getCompleted(long user_id) {
		Student student = studentRepository.findByUserId(user_id);
		if (Objects.isNull(student))
			throw new StudentNotFoundException("You are not a student");
		return courseRepository.getCompletedCourse(student.getId());
	}

	public Double getPercent(int course_id, long user_id) {
		Student student = studentRepository.findByUserId(user_id);
		if (Objects.isNull(student))
			throw new StudentNotFoundException("You are not a student");
		return courseRepository.getCompletePercent(course_id, student.getId());
	}

	public Byte getLastTaskNumber(int courseId, Long userId) {
		Student student = studentRepository.findByUserId(userId);
		if (Objects.isNull(student))
			throw new RuntimeException("You are not a student");
		return courseRepository.getLastTaskNumber(courseId, student.getId());
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
