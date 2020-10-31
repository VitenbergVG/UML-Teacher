package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import umlteacher.model.dao.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
