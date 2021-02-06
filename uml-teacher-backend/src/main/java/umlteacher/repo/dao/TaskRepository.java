package umlteacher.repo.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import umlteacher.model.dao.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	Task findById(int task_id);
	
	@Query(value = "SELECT * FROM task t INNER JOIN course_task ct ON t.task_id = ct.task_id WHERE course_id = ?1 AND task_number = ?2", nativeQuery = true)
	Task findByCourseIdAndTaskNumber(int course_id, byte task_number);
	
	@Query(value = "SELECT * FROM task t INNER JOIN course_task ct ON t.task_id = ct.task_id WHERE course_id = ?1 ORDER BY ct.task_number ASC", nativeQuery = true)
	Set<Task> getByCourseIdAndSortByNumber(int course_id);
}
