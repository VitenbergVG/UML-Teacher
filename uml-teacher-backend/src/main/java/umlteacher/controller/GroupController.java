package umlteacher.controller;

import java.security.Principal;
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
import umlteacher.exceptions.CourseNotFoundException;
import umlteacher.exceptions.EmployeeNotFoundException;
import umlteacher.exceptions.GroupNotFoundException;
import umlteacher.exceptions.StudentNotFoundException;
import umlteacher.model.dao.Group;
import umlteacher.model.dao.User;
import umlteacher.service.dao.GroupService;
import umlteacher.service.dao.UserServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/group")
public class GroupController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping("/get")
	public Object getById(@RequestParam(required = false) Integer group_id) throws GroupNotFoundException {
		if (Objects.isNull(group_id))
			return groupService.findAll();
		return groupService.findGroupById(group_id);
	}
	
	@PostMapping("/save")
	public Group save(@RequestBody Group group) throws EmployeeNotFoundException, CourseNotFoundException, BadFiledValueException {
		return groupService.save(group);
	}
	
	@PostMapping("/add-students")
	public void addStudents(@RequestParam int group_id, @RequestBody int[] student_ids) throws GroupNotFoundException, StudentNotFoundException {
		groupService.addStudents(group_id, student_ids);
	}
	
	@PostMapping("/remove-students")
	public void removeStudents(@RequestParam int group_id, @RequestBody int[] student_ids) throws GroupNotFoundException {
		groupService.removeStudents(group_id, student_ids);
	}
	
	@GetMapping("/join")
	public void join(@RequestParam int group_id, Principal principal) {
		User user = (User) userServiceImpl.loadUserByUsername(principal.getName());
		groupService.join(group_id, user.getId());
	}
	
	@GetMapping("/delete")
	public void delete(@RequestParam int group_id) throws GroupNotFoundException {
		groupService.delete(group_id);
	}
}
