package umlteacher.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import umlteacher.exceptions.StudentNotFoundException;
import umlteacher.service.dao.StudentService;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/get")
	public Object get(@RequestParam(required = false) Integer student_id, @RequestParam(required = false) Integer group_id) throws StudentNotFoundException {
		if (Objects.isNull(student_id))
			if (Objects.isNull(group_id))
				return studentService.findAll();
			else
				return studentService.findByGroupId(group_id);
		else
			return studentService.findById(student_id);
	}

}
