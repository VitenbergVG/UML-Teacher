package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import umlteacher.model.dao.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
