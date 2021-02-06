package umlteacher.repo.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import umlteacher.model.dao.CourseTask;

public interface CourseTaskRepository extends JpaRepository<CourseTask, Integer> {
	@Query(value = "SELECT * FROM course_task WHERE course_id = ?1", nativeQuery = true)
	Set<CourseTask> fingByCourseId(int course_id);
	
	@Query(value = "SELECT * FROM course_task WHERE course_id = ?1 AND task_number = ?2", nativeQuery = true)
	CourseTask getByCourseIdAndNumber(int course_id, byte number);
}
