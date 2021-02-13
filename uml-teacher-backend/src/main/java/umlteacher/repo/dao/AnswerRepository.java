package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umlteacher.model.dao.Answer;

import java.util.Set;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	Answer getById(int answer_id);
	
	@Query(value = "SELECT * FROM answer WHERE course_id = ?1", nativeQuery = true)
	Set<Answer> getByCourseId(int course_id);
	
	@Query(value = "SELECT * FROM answer WHERE course_id = ?1 AND correct IS NULL", nativeQuery = true)
	Set<Answer> getByCourseIdAndUnchecked(int course_id);

    @Query(value = "SELECT * FROM answer WHERE course_id = ?1 AND correct IS NOT NULL", nativeQuery = true)
    Set<Answer> getByCourseIdAndChecked(int course_id);

    @Query(value = "SELECT * FROM answer WHERE student_id = ?1", nativeQuery = true)
    Set<Answer> getByStudentId(int student_id);

    @Query(value = "SELECT * FROM answer WHERE course_id = ?1 and task_id = ?2", nativeQuery = true)
    Answer getByCourseIdAndTaskId(int course_id, int task_id);

    @Query(value = "SELECT * FROM answer a join course_task ct" +
            " ON a.course_id = ct.course_id and a.task_id = ct.task_id" +
            " WHERE a.course_id = ?1 and task_number = ?2", nativeQuery = true)
    Answer getByCourseIdAndTaskNumber(int task_id, byte task_number);
}
