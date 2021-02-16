package umlteacher.model.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import umlteacher.model.CourseTaskInfo;
import umlteacher.model.TaskType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
public class MultipleTestCourseTask extends CourseTaskInfo {

    List<TestCourseTask> testTasks;

    public MultipleTestCourseTask(int taskId) {
        super(TaskType.TEST, taskId);
        testTasks = new ArrayList<>();
    }

    @Override
    public void getTaskFromFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        TestCourseTask tmpTask = null;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String nextLine = (i + 1) < lines.size() ? lines.get(i + 1) : line;
            if (line.contains(QUESTION_ANCHOR)) {
                tmpTask = new TestCourseTask();
                tmpTask.setQuestion(nextLine);
                continue;
            }
            if (line.contains(ANSWERS_ANCHOR)) {
                Objects.requireNonNull(tmpTask).setAnswers(Arrays.asList(nextLine.split(",")));
                continue;
            }
            if (line.contains(CORRECT_ANSWER_ANCHOR)) {
                Objects.requireNonNull(tmpTask).setCorrectAnswer(nextLine);
                testTasks.add(tmpTask);
                tmpTask = null;
            }
        }
    }
}
