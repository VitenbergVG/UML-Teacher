package umlteacher.service.dao;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import umlteacher.exceptions.BadFiledValueException;
import umlteacher.exceptions.EmployeeNotFoundException;
import umlteacher.exceptions.UserNotFoundException;
import umlteacher.model.dao.Employee;
import umlteacher.model.dao.Student;
import umlteacher.repo.dao.EmployeeRepository;
import umlteacher.repo.dao.StudentRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	public Employee findById(int employee_id) throws EmployeeNotFoundException {
		Employee employee = employeeRepository.findById(employee_id);
		if (Objects.isNull(employee))
			throw new EmployeeNotFoundException(employee_id);
		return employee;
	}
	
	public Employee save(Employee employee) throws UserNotFoundException, BadFiledValueException {
		Student student = studentRepository.findByUserId(employee.getId());
		if (Objects.nonNull(student))
			throw new BadFiledValueException("user_id " + employee.getId() + " already used with student_id " + student.getId());
		
		Employee e = employeeRepository.findByUserId(employee.getId());
		if (Objects.nonNull(e))
			throw new BadFiledValueException("user_id " + employee.getId() + " already used with employee_id " + e.getId());		
		
		return employeeRepository.save(employee);
		
	}
	
	public void delete(int employee_id) {
		employeeRepository.deleteById(employee_id);
	}
}
