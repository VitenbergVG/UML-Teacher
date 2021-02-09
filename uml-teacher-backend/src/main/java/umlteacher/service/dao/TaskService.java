package umlteacher.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umlteacher.exceptions.CourseNotFoundException;
import umlteacher.exceptions.FileParsingException;
import umlteacher.exceptions.TaskNotFoundException;
import umlteacher.model.CourseTaskInfo;
import umlteacher.model.TaskType;
import umlteacher.model.dao.Course;
import umlteacher.model.dao.CourseTask;
import umlteacher.model.dao.Task;
import umlteacher.model.impl.GraphicCourseTask;
import umlteacher.model.impl.MultipleTestCourseTask;
import umlteacher.model.impl.TextCourseTask;
import umlteacher.repo.dao.CourseRepository;
import umlteacher.repo.dao.CourseTaskRepository;
import umlteacher.repo.dao.TaskRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class TaskService {

    private final String CONFIG_PATH = "src/main/config";
    private final String PATH_TO_TEST_TASKS = "task/test";
    private final String PATH_TO_TEXT_TASKS = "task/text";
    private final String PATH_TO_GRAPHIC_TASKS = "task/graphic";
    private final String TXT_EXTENSION = ".txt";

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

    public List<CourseTaskInfo> getCourseTasks(int courseId) throws FileParsingException {
        List<CourseTaskInfo> tasks = new ArrayList<>();
        Set<Task> tasksFromDb = getByCourseId(courseId);
        Set<CourseTask> courseTasks = courseTaskRepository.findByCourseId(courseId);
        tasksFromDb.forEach(task -> {
            CourseTask ct = courseTasks.stream()
                    .filter(courseTask -> courseTask.getId() == task.getId())
                    .findFirst().orElse(null);
            byte taskNumber = Objects.nonNull(ct) ? ct.getNumber() : 0;
            Path pathToFile = Paths.get(CONFIG_PATH, task.getPath() + TXT_EXTENSION);
            try {
                tasks.add(getCourseTaskInfo(pathToFile, taskNumber, task));
            } catch (IOException e) {
                throw new FileParsingException("Can't parse file " + task.getPath());
            }
        });
        tasks.sort(Comparator.comparingInt(CourseTaskInfo::getTaskNumber));
        return tasks;
    }

    private CourseTaskInfo getCourseTaskInfo(Path path, byte taskNumber, Task task) throws IOException {
        CourseTaskInfo taskInfo = null;
        if (task.getPath().contains(PATH_TO_TEST_TASKS)) {
            taskInfo = new MultipleTestCourseTask(taskNumber, TaskType.TEST);
        }
        if (task.getPath().contains(PATH_TO_TEXT_TASKS)) {
            taskInfo = new TextCourseTask(taskNumber, TaskType.TEXT);
        }
        if (task.getPath().contains(PATH_TO_GRAPHIC_TASKS)) {
            taskInfo = new GraphicCourseTask(taskNumber, TaskType.TEXT);
        }
        List<String> lines = Files.readAllLines(path);
        Objects.requireNonNull(taskInfo).fillTaskFromFileContent(lines);
        return taskInfo;
    }
}
