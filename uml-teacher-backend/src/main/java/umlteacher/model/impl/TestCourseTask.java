package umlteacher.model.impl;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestCourseTask {

    private String question;
    private List<String> answers;
    private String correctAnswer;

    public TestCourseTask() {
        this.answers = new ArrayList<>();
    }
}