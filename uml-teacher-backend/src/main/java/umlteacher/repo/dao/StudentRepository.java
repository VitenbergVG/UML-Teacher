package umlteacher.repo.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import umlteacher.model.dao.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	Student findById(int student_id);
	
	@Query(value = "SELECT * FROM student s INNER JOIN student_group sg ON s.student_id = sg.student_id WHERE group_id = ?1", nativeQuery = true)
	Set<Student> findByGroupId(int group_id);
	
	@Query(value = "SELECT * FROM student WHERE user_id = ?1", nativeQuery = true)
	Student findByUserId(long user_id);
	
	@Query(value = "SELECT * FROM student WHERE student_id = ANY(?1)", nativeQuery = true)
	Set<Student> findByIds(int[] student_ids);
}
