package umlteacher.model.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import umlteacher.model.CourseTaskInfo;
import umlteacher.model.TaskType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TextCourseTask extends CourseTaskInfo {

    private String question;
    private String answer;

    public TextCourseTask() {
        super(TaskType.TEXT);
    }

    @Override
    public void getTaskFromFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String nextLine = (i + 1) < lines.size() ? lines.get(i + 1) : line;
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
