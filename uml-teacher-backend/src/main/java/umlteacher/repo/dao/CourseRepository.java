package umlteacher.repo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umlteacher.model.dao.Course;

import java.util.List;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findById(int id);

    @Query(value = "select course.* from groups \n" +
            "join course on groups.current_course_id = course.course_id\n" +
            "where first_teacher_id = ?1 or second_teacher_id = ?1", nativeQuery = true)
    List<Course> findByEmployeeId(int employeeId);

    @Query(value = "select cast(count(a.task_id) as real) / count(ct.task_id) * 100 "
            + "from course_task ct "
            + "left outer join (SELECT * FROM answer where student_id = ?2 and course_id = ?1 and correct) a "
            + "on a.task_id = ct.task_id AND ct.course_id = a.course_id "
            + "where ct.course_id = ?1", nativeQuery = true)
    Double getCompletePercent(int course_id, int student_id);

    @Query(value = "select c.course_id, c.course_name, c.created_date, c.rating, c.time_to_complete from course c join ("
            + "select ct.course_id, cast(count(a.task_id) as real) / count(ct.task_id) * 100 as p "
            + "from course_task ct "
            + "left outer join (SELECT * FROM answer where student_id = ?1 and correct) a on a.task_id = ct.task_id AND ct.course_id = a.course_id "
            + "group by ct.course_id) ttt on c.course_id = ttt.course_id "
            + "where ttt.p = 100", nativeQuery = true)
    Set<Course> getCompletedCourse(int student_id);

    @Query(value = "select CASE WHEN COUNT(*) > 0 THEN true ELSE false END "
            + "from course c join groups g on c.course_id = g.group_id "
            + "where c.course_id = ?1 and (g.first_teacher_id = ?2 or g.second_teacher_id = ?2)", nativeQuery = true)
    boolean isOwnCourse(int course_id, int employee_id);

    @Query(value = "select coalesce(" +
            "(select max(task_number) as task_number from course_task " +
            "join answer on course_task.task_id = answer.task_id " +
            "where course_task.course_id = ?1 and student_id = ?2), " +
            "(select task_number from course_task where course_task.course_id = ?1 limit 1))", nativeQuery = true)
    Byte getLastTaskNumber(int courseId, int studentId);
}
