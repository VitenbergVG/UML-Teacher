package umlteacher.model.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import umlteacher.model.CourseTaskInfo;
import umlteacher.model.TaskType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Data
@EqualsAndHashCode(callSuper = true)
public class GraphicCourseTask extends CourseTaskInfo {

    private String question;

    public GraphicCourseTask(int taskId) {
        super(TaskType.GRAPHIC, taskId);
    }

    @Override
    public void getTaskFromFile(Path path) throws IOException {
        question = Files.readAllLines(path).get(0);
    }
}
