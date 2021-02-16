package umlteacher.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umlteacher.exceptions.*;
import umlteacher.model.dao.Employee;
import umlteacher.model.dao.Student;
import umlteacher.model.dao.User;
import umlteacher.repo.dao.EmployeeRepository;
import umlteacher.repo.dao.GroupRepository;
import umlteacher.repo.dao.StudentRepository;
import umlteacher.repo.dao.UserRepository;

import java.util.*;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	public Student findById(int student_id) throws StudentNotFoundException {
		Student student = studentRepository.findById(student_id);
		if (Objects.isNull(student)) {
			throw new StudentNotFoundException(student_id);
		}
		return student;
	}

	public Map<Long, String> getStudentNameByUserId(int student_id) {
		User user = userRepository.getUserByStudentId(student_id);
		return Collections.singletonMap(user.getId(), user.getFullname());
	}

	public Set<Student> findByIds(int[] student_ids) throws StudentNotFoundException {
		Set<Student> result = studentRepository.findByIds(student_ids);
		List<Student> tmp = new ArrayList<Student>(result);
		for (int id : student_ids) {
			int i = 0;
			while (i < result.size() && id != tmp.get(i).getId())
				i++;
			if (i == result.size())
				throw new StudentNotFoundException(id);
		}
		return result;
	}
	
	public Student save(Student student) throws UserNotFoundException, EduNotFoundException, GroupNotFoundException, BadFiledValueException {
		Employee employee = employeeRepository.findByUserId(student.getUser().getId());
		if (Objects.nonNull(employee))
			throw new BadFiledValueException("user_id " + student.getUser().getId() + " already used by employee_id " + employee.getId());
		
		Student s = studentRepository.findByUserId(student.getUser().getId());
		if (Objects.nonNull(s))
			throw new BadFiledValueException("user_id " + student.getUser().getId() + " already used with student_id " + s.getId());
		
		return studentRepository.save(student);
	}
	
	public void delete(int student_id) {
		studentRepository.deleteById(student_id);
	}
	
	public Set<Student> findByGroupId(int group_id) throws GroupNotFoundException {
		if (Objects.isNull(groupRepository.findById(group_id)))
			throw new GroupNotFoundException("Group not found by group_id " + group_id);
		return studentRepository.findByGroupId(group_id);
	}

}
