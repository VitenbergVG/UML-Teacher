package umlteacher.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import umlteacher.model.dao.Task;
import umlteacher.service.dao.TaskService;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskService taskService;
	@Autowired
	private ObjectMapper mapper;
	
	@GetMapping("/get")
	public Object get(@RequestParam(required = false) Integer task_id,
					  @RequestParam(required = false) Integer course_id,
					  @RequestParam(required = false) Byte task_number) throws MissingServletRequestParameterException {
		
		if (Objects.nonNull(task_id))
			return taskService.getById(task_id);
		if (Objects.nonNull(course_id))
			if (Objects.isNull(task_number)) {
				List<Task> tasks = new ArrayList<Task>(taskService.getByCourseId(course_id));
				ArrayNode response = mapper.createArrayNode();
				for (int i = 0; i < tasks.size(); i++) {
					ObjectNode task = mapper.valueToTree(tasks.get(i));
					task.set("number", mapper.valueToTree(i));
					response.add(task);
				}
				return response;
			}
			else {
				return taskService.getByCourseIdAndTaskNumber(course_id, task_number);
			}
		throw new MissingServletRequestParameterException("task_id", "integer");
	}

}
