package umlteacher.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Path;

@NoArgsConstructor
@AllArgsConstructor
public abstract class CourseTaskInfo {

    protected final String QUESTION_ANCHOR = "$QUESTION$";
    protected final String ANSWERS_ANCHOR = "$ANSWERS$";
    protected final String CORRECT_ANSWER_ANCHOR = "$CORRECT_ANSWER$";

    @Getter
    @Setter
    private TaskType type;

    @Getter
    @Setter
    private int taskId;

    public abstract void getTaskFromFile(Path path) throws IOException;
}
