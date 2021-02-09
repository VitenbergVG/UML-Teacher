package umlteacher.model.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import umlteacher.model.CourseTaskInfo;
import umlteacher.model.TaskType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
public class MultipleTestCourseTask extends CourseTaskInfo {

    List<TestCourseTask> testTasks;

    public MultipleTestCourseTask(byte taskNumber, TaskType type) {
        super(taskNumber, type);
        testTasks = new ArrayList<>();
    }

    @Override
    public void fillTaskFromFileContent(List<String> contentLines) {
        TestCourseTask tmpTask = null;
        for (int i = 0; i < contentLines.size(); i++) {
            String line = contentLines.get(i);
            String nextLine = (i + 1) < contentLines.size() ? contentLines.get(i + 1) : line;
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
