package umlteacher.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umlteacher.model.dao.Course;
import umlteacher.model.dao.Group;
import umlteacher.repo.dao.CourseRepository;
import umlteacher.repo.dao.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LearningServiceImpl {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public LearningServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getCurrentCoursesForUser(Long userId) {
        return studentRepository.findByUserId(userId)
                .getGroups().stream()
                .map(Group::getCurrentCourse)
                .collect(Collectors.toList());
    }
}
