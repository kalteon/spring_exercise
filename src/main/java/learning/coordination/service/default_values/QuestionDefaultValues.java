package learning.coordination.service.default_values;

import java.util.Arrays;
import java.util.List;

public class QuestionDefaultValues {
    public static final String QUESTION_TEMPLATE = "As a teacher, please give me FIELD materials for students of level DIFFICULTY to learn";
    public static final List<String> QUESTION_DIFFICULTIES = Arrays.asList("A1", "A2", "B1", "B2", "C1", "C2");
    public static final List<String> QUESTION_FIELDS = Arrays.asList("speaking", "listening", "reading", "writing");
    public static final String EMPTY_PROMPT = "empty prompt";
    public static final String KEYWORD1 = "DIFFICULTY";
    public static final String KEYWORD2 = "FIELD";
}
