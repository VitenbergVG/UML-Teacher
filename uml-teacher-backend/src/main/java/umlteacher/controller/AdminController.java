package umlteacher.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import umlteacher.exceptions.BadFiledValueException;
import umlteacher.exceptions.EduNotFoundException;
import umlteacher.exceptions.GroupNotFoundException;
import umlteacher.exceptions.UserNotFoundException;
import umlteacher.model.dao.Employee;
import umlteacher.model.dao.Student;
import umlteacher.service.dao.EmployeeService;
import umlteacher.service.dao.StudentService;
import umlteacher.service.dao.UserServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserServiceImpl userServiceImpl;
    
    @GetMapping("/user/get")
    public Object get(@RequestParam(required = false) Long user_id) throws UserNotFoundException {
    	if (Objects.isNull(user_id))
    		return userServiceImpl.getAllUsers();
    	else
    		return userServiceImpl.findUserById(user_id);
    }
    
    @PostMapping("/add-student")
    public Student saveStudent(@RequestBody Student student) throws UserNotFoundException, EduNotFoundException, GroupNotFoundException, BadFiledValueException {
    	return studentService.save(student);
    }
    
    @GetMapping("/delete-student")
    public void deleteStudent(@RequestParam int student_id) {
    	studentService.delete(student_id);
    }
    
    @PostMapping("/add-employee")
    public Employee saveEmployee(@RequestBody Employee employee) throws UserNotFoundException, BadFiledValueException {
    	return employeeService.save(employee);
    }
    
    @GetMapping("/delete-employee")
    public void deleteEmployee(@RequestParam int employee_id) {
    	employeeService.delete(employee_id);
    }
    
    @GetMapping("/delete")
    public boolean deleteUser(@RequestParam Long user_id) {
    	return userServiceImpl.deleteUser(user_id);
    }

}
