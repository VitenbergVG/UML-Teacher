package umlteacher.model.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import umlteacher.model.CourseTaskInfo;
import umlteacher.model.TaskType;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TextCourseTask extends CourseTaskInfo {

    private String question;
    private String answer;

    public TextCourseTask(byte taskNumber, TaskType type) {
        super(taskNumber, type);
    }

    @Override
    public void fillTaskFromFileContent(List<String> contentLines) {
        for (int i = 0; i < contentLines.size(); i++) {
            String line = contentLines.get(i);
            String nextLine = (i + 1) < contentLines.size() ? contentLines.get(i + 1) : line;
            if (line.contains(QUESTION_ANCHOR)) {
                this.question = nextLine;
                continue;
            }
            if (line.contains(CORRECT_ANSWER_ANCHOR)) {
                this.answer = nextLine;
            }
        }
    }
}
