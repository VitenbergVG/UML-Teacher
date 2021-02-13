package umlteacher.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umlteacher.exceptions.CourseNotFoundException;
import umlteacher.exceptions.FileParsingException;
import umlteacher.exceptions.TaskNotFoundException;
import umlteacher.model.CourseTaskInfo;
import umlteacher.model.dao.Course;
import umlteacher.model.dao.CourseTask;
import umlteacher.model.dao.Task;
import umlteacher.model.impl.GraphicCourseTask;
import umlteacher.model.impl.MultipleTestCourseTask;
import umlteacher.model.impl.TextCourseTask;
import umlteacher.repo.dao.CourseRepository;
import umlteacher.repo.dao.CourseTaskRepository;
import umlteacher.repo.dao.StudentRepository;
import umlteacher.repo.dao.TaskRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

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
    @Autowired
    private StudentRepository studentRepository;

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

    public Map<Byte, CourseTaskInfo> getCourseTasks(int courseId) throws FileParsingException {
        Map<Byte, CourseTaskInfo> tasks = new TreeMap<>();
        Set<Task> tasksFromDb = getByCourseId(courseId);
        Set<CourseTask> courseTasks = courseTaskRepository.findByCourseId(courseId);
        tasksFromDb.forEach(task -> {
            CourseTask ct = courseTasks.stream()
                    .filter(courseTask -> courseTask.getTask_id() == task.getId())
                    .findFirst().orElse(null);
            byte taskNumber = Objects.nonNull(ct) ? ct.getNumber() : 0;
            Path pathToFile = Paths.get(CONFIG_PATH, task.getPath() + TXT_EXTENSION);
            CourseTaskInfo taskInfo = getCourseTaskInstanceByPath(task.getPath());
            try {
                taskInfo.getTaskFromFile(pathToFile);
            } catch (IOException e) {
                throw new FileParsingException("Can't parse file " + task.getPath());
            }
            tasks.put(taskNumber, taskInfo);
        });
        return tasks;
    }

    private CourseTaskInfo getCourseTaskInstanceByPath(String path) {
        if (path.contains(PATH_TO_TEST_TASKS)) {
            return new MultipleTestCourseTask();
        }
        if (path.contains(PATH_TO_TEXT_TASKS)) {
            return new TextCourseTask();
        }
        if (path.contains(PATH_TO_GRAPHIC_TASKS)) {
            return new GraphicCourseTask();
        }
        throw new IllegalArgumentException("Could not create task instance for path: ");
    }
}
