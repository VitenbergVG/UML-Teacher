package umlteacher.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umlteacher.exceptions.AnswerNotFoundException;
import umlteacher.exceptions.EmployeeNotFoundException;
import umlteacher.exceptions.StudentNotFoundException;
import umlteacher.exceptions.TaskNotFoundException;
import umlteacher.model.dao.Answer;
import umlteacher.model.dao.Employee;
import umlteacher.model.dao.Student;
import umlteacher.model.dao.Task;
import umlteacher.repo.dao.*;

import java.util.Objects;
import java.util.Set;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TaskRepository taskRepository;

    public Answer addAnswer(long user_id, int course_id, int task_id, String answer, Boolean isCorrect) {
        Student student = studentRepository.findByUserId(user_id);
        if (Objects.isNull(student))
            throw new StudentNotFoundException("You are not a student");

        Task task = taskRepository.findById(task_id);
        if (Objects.isNull(task)) {
            throw new TaskNotFoundException(task_id);
        }

        Answer a = answerRepository.getByCourseIdAndTaskId(course_id, task_id);
        if (Objects.isNull(a)) {
            a = new Answer();
            a.setCourse_id(course_id);
            a.setStudent_id(student.getId());
            a.setTask_id(task_id);
            if (Objects.nonNull(isCorrect)) {
                a.setIsCorrect(isCorrect);
            }
        }

        a.setAnswer(answer);

        return answerRepository.save(a);
    }

    public Answer addAnswer(long user_id, int course_id, byte task_number, String answer, Boolean isCorrect) {
        Student student = studentRepository.findByUserId(user_id);
        if (Objects.isNull(student))
            throw new StudentNotFoundException("You are not a student");

        Answer a = answerRepository.getByCourseIdAndTaskNumberAndStudentId(course_id, student.getId(), task_number);
        Task task = taskRepository.findByCourseIdAndTaskNumber(course_id, task_number);
        if (Objects.isNull(task)) {
            throw new TaskNotFoundException("Task not found by course_id "
                    + course_id + " and task_number " + task_number);
        }
        if (Objects.isNull(a)) {
            a = new Answer();
            a.setCourse_id(course_id);
            a.setStudent_id(student.getId());
            a.setTask_id(task.getId());
        }

        a.setAnswer(answer);
        if (Objects.nonNull(isCorrect)) {
            a.setIsCorrect(isCorrect);
        }

        return answerRepository.save(a);
    }

    public Set<Answer> getByCourseId(int course_id, Boolean unchecked) {
        if (Objects.nonNull(unchecked))
            if (unchecked)
                return answerRepository.getByCourseIdAndUnchecked(course_id);
            else
                return answerRepository.getByCourseIdAndChecked(course_id);
        else
            return answerRepository.getByCourseId(course_id);
    }

    public Answer getByCourseIdAndTaskNumberAndStudentId(int course_id, long studentUserId, byte taskNumber) {
        Student student = studentRepository.findByUserId(studentUserId);
        if (Objects.isNull(student))
            throw new StudentNotFoundException("You are not a student");
        Answer answer = answerRepository.getByCourseIdAndTaskNumberAndStudentId(course_id, student.getId(), taskNumber);
        if (Objects.isNull(answer))
            throw new AnswerNotFoundException("Answer not found by course_id "
                    + course_id + " and taskNumber " + taskNumber);
        return answer;
    }

    public void check(int answer_id, long user_id, boolean isCorrect) {
        Answer answer = answerRepository.getById(answer_id);
        if (Objects.isNull(answer)) {
            throw new AnswerNotFoundException(answer_id);
        }

        Employee employee = employeeRepository.findByUserId(user_id);
        if (Objects.isNull(employee))
            throw new EmployeeNotFoundException("You are not an employee");

        if (courseRepository.isOwnCourse(answer.getCourse_id(), employee.getId())) {
            answer.setIsCorrect(isCorrect);
            answerRepository.save(answer);
        } else
            throw new RuntimeException("You are not the owner of the course with course_id " + answer.getCourse_id());
    }

}
