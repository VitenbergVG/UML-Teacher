package umlteacher.service.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import umlteacher.exceptions.BadFiledValueException;
import umlteacher.exceptions.EduNotFoundException;
import umlteacher.exceptions.GroupNotFoundException;
import umlteacher.exceptions.StudentNotFoundException;
import umlteacher.exceptions.UserNotFoundException;
import umlteacher.model.dao.Employee;
import umlteacher.model.dao.Student;
import umlteacher.repo.dao.EmployeeRepository;
import umlteacher.repo.dao.GroupRepository;
import umlteacher.repo.dao.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
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
