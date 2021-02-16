package umlteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umlteacher.exceptions.BadFiledValueException;
import umlteacher.exceptions.EduNotFoundException;
import umlteacher.exceptions.GroupNotFoundException;
import umlteacher.exceptions.UserNotFoundException;
import umlteacher.model.dao.Employee;
import umlteacher.model.dao.Student;
import umlteacher.service.dao.EmployeeService;
import umlteacher.service.dao.StudentService;
import umlteacher.service.dao.UserServiceImpl;

import java.util.Objects;

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
    public Object get(@RequestParam(required = false) Long userId) throws UserNotFoundException {
        if (Objects.isNull(userId))
            return userServiceImpl.getAllUsers();
        else
            return userServiceImpl.findUserById(userId);
    }
    
    @PostMapping("/add-student")
    public Student saveStudent(@RequestBody Student student) throws UserNotFoundException, EduNotFoundException, GroupNotFoundException, BadFiledValueException {
    	return studentService.save(student);
    }

    @GetMapping("/delete-student")
    public void deleteStudent(@RequestParam int studentId) {
        studentService.delete(studentId);
    }
    
    @PostMapping("/add-employee")
    public Employee saveEmployee(@RequestBody Employee employee) throws UserNotFoundException, BadFiledValueException {
    	return employeeService.save(employee);
    }

    @GetMapping("/delete-employee")
    public void deleteEmployee(@RequestParam int employeeId) {
        employeeService.delete(employeeId);
    }

    @GetMapping("/delete")
    public boolean deleteUser(@RequestParam Long userId) {
        return userServiceImpl.deleteUser(userId);
    }

    @GetMapping("/change-role")
    public ResponseEntity changeRoleForUser(@RequestParam("userId") Long userId,
                                            @RequestParam("newRoleName") String newRoleName)
            throws UserNotFoundException {
        userServiceImpl.changeRoleForUser(userId, newRoleName);
        return ResponseEntity.ok(200);
    }
}
