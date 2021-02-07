package umlteacher.service.dao;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import umlteacher.exceptions.CourseNotFoundException;
import umlteacher.exceptions.TaskNotFoundException;
import umlteacher.model.dao.Course;
import umlteacher.model.dao.CourseTask;
import umlteacher.model.dao.Task;
import umlteacher.repo.dao.CourseRepository;
import umlteacher.repo.dao.CourseTaskRepository;
import umlteacher.repo.dao.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private CourseTaskRepository courseTaskRepository;
	
	public Task getById(int task_id) throws TaskNotFoundException {
		Task task = taskRepository.findById(task_id);
		if (Objects.isNull(task))
			throw new TaskNotFoundException(task_id);
		return task;
	}
	
	public Set<Task> getByCourseId(int course_id) {
		Course course = courseRepository.findById(course_id);
		if (Objects.isNull(course))
			throw new CourseNotFoundException(course_id);
		return taskRepository.getByCourseIdAndSortByNumber(course_id);
	}
	
	public Task getByCourseIdAndTaskNumber(int course_id, byte task_number) {
		Task task = taskRepository.findByCourseIdAndTaskNumber(course_id, task_number);
		if (Objects.isNull(task))
			throw new TaskNotFoundException("Task not found by course_id " + course_id + " and number " + task_number);
		return task;
	}
	
	public Task save(String task_path, int course_id, byte task_number) {
		Course course = courseRepository.findById(course_id);
		if (Objects.isNull(course))
			throw new CourseNotFoundException(course_id);
		
		CourseTask ct = courseTaskRepository.getByCourseIdAndNumber(course_id, task_number);
		Task task = taskRepository.findByCourseIdAndTaskNumber(course_id, task_number);
		if (Objects.isNull(ct)) {
			ct = new CourseTask();
			ct.setCourse_id(course_id);
			ct.setNumber(task_number);
			task = new Task();
		}
		
		task.setPath(task_path);
		ct.setTask_id(task.getId());
		
		task = taskRepository.save(task);
		courseTaskRepository.save(ct);
		
		return task;
	}
}
