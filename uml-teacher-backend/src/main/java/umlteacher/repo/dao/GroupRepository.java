package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umlteacher.model.dao.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    Group findById(int id);

    Group findByCurrentCourseId(int currentCourseId);

    @Query(value = "select users.user_fullname from groups " +
            "join employee on groups.first_teacher_id = employee.employee_id " +
            "join users on employee.user_id = users.user_id " +
            "where current_course_id = ?1", nativeQuery = true)
    String findTeacherNameByCourseId(int courseId);
}
