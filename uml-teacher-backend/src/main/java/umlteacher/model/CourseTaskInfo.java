package umlteacher.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public abstract class CourseTaskInfo {

    protected final String QUESTION_ANCHOR = "$QUESTION$";
    protected final String ANSWERS_ANCHOR = "$ANSWERS$";
    protected final String CORRECT_ANSWER_ANCHOR = "$CORRECT_ANSWER$";

    @Getter
    @Setter
    private byte taskNumber;
    @Getter
    @Setter
    private TaskType type;

    public abstract void fillTaskFromFileContent(List<String> contentLines);
}
