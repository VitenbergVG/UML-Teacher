package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umlteacher.model.dao.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "select users.* from users " +
            "join student on users.user_id = student.user_id " +
            "where student.student_id = ?1", nativeQuery = true)
    User getUserByStudentId(int studentId);
}
