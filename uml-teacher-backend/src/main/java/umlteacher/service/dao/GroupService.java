package umlteacher.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umlteacher.exceptions.*;
import umlteacher.model.dao.Group;
import umlteacher.model.dao.Student;
import umlteacher.repo.dao.GroupRepository;
import umlteacher.repo.dao.StudentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private StudentRepository studentRepository;

    public Group findGroupById(int group_id) throws GroupNotFoundException {
        Group group = groupRepository.findById(group_id);
        if (Objects.isNull(group)) {
            throw new GroupNotFoundException(group_id);
        }
        return group;
    }

    public Group findGroupByCourseId(int courseId) throws GroupNotFoundException {
        Group group = groupRepository.findByCurrentCourseId(courseId);
        if (Objects.isNull(group)) {
            throw new GroupNotFoundException("Group not found by courseId " + courseId);
        }
        return group;
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public String findTeacherNameByCourseId(int courseId) {
        return groupRepository.findTeacherNameByCourseId(courseId);
    }

    public Group save(Group group) throws EmployeeNotFoundException, CourseNotFoundException, BadFiledValueException {
        Group g = groupRepository.findById(group.getId());
        if (Objects.isNull(g)) {
            g = new Group();
        }
        group.setStudents(g.getStudents());
        return groupRepository.save(group);
    }

	public void addStudents(int group_id, int[] student_ids) throws GroupNotFoundException, StudentNotFoundException {
		Group group = findGroupById(group_id);
		Set<Student> new_students = studentRepository.findByIds(student_ids);
		Set<Student> students = group.getStudents();
		students.addAll(new_students);
		group.setStudents(students);
		groupRepository.save(group);
	}


    public void removeStudents(int group_id, int[] student_ids) throws GroupNotFoundException {
		Group group = findGroupById(group_id);
		Set<Student> tmp = group.getStudents();
		tmp.removeIf(student -> Arrays.stream(student_ids).anyMatch(i -> i == student.getId()));
		group.setStudents(tmp);
		groupRepository.save(group);
	}

    public void join(int group_id, long user_id) {
		Group group = findGroupById(group_id);
		Student student = studentRepository.findByUserId(user_id);
		if (Objects.isNull(student))
			throw new RuntimeException("You are not a student");
		student.getGroups().add(group);
		studentRepository.save(student);
	}

    public void delete(int group_id) throws GroupNotFoundException {
		Group group = groupRepository.findById(group_id);
		if (Objects.isNull(group)) {
			throw new GroupNotFoundException(group_id);
		}
		groupRepository.deleteById(group_id);
	}
}
