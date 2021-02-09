package umlteacher.model.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import umlteacher.model.CourseTaskInfo;
import umlteacher.model.TaskType;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GraphicCourseTask extends CourseTaskInfo {

    private byte[] task;

    public GraphicCourseTask(byte taskNumber, TaskType type) {
        super(taskNumber, type);
    }

    @Override
    public void fillTaskFromFileContent(List<String> contentLines) {
        // TODO implement this method
    }
}
