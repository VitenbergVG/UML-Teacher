package umlteacher.model.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import umlteacher.model.CourseTaskInfo;
import umlteacher.model.TaskType;

import java.nio.file.Path;

@Data
@EqualsAndHashCode(callSuper = true)
public class GraphicCourseTask extends CourseTaskInfo {

    private byte[] task;

    public GraphicCourseTask() {
        super(TaskType.GRAPHIC);
    }

    @Override
    public void getTaskFromFile(Path path) {
        // TODO implement this method
    }
}
