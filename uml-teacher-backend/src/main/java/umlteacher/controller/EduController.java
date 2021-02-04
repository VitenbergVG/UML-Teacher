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

import umlteacher.exceptions.EduNotFoundException;
import umlteacher.model.dao.Edu;
import umlteacher.service.dao.EduService;

@RestController
@CrossOrigin
@RequestMapping("/edu")
public class EduController {
	@Autowired
	private EduService eduService;
	
	@GetMapping("/get")
	public Object getById(@RequestParam(required = false) Integer edu_id) throws EduNotFoundException {
		if (Objects.isNull(edu_id))
			return eduService.findAll();
		return eduService.findById(edu_id);
	}
	
	@PostMapping("/save")
	public Edu save(@RequestBody Edu edu) {
		return eduService.save(edu);
	}
	
	@GetMapping("/delete")
	public void delete(@RequestParam int edu_id) {
		eduService.delete(edu_id);
	}

}
