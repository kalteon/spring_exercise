package learning.coordination.service.default_values;

import java.util.Arrays;
import java.util.List;

public class QuestionDefaultValues {
    public static final String TEMPLATE = "As a English teacher, please give me FIELD materials for students of level DIFFICULTY to learn about TOPIC." +
            " Can you provide me with a 500-word article on this subject?";
    public static final List<String> DIFFICULTIES = Arrays.asList("beginner (A1-A2)", "intermediate (B1-B2)", "advanced (C1-C2)");
    public static final List<String> FIELDS = Arrays.asList("speaking skills", "listening comprehension", "reading comprehension", "writing skills");
    public static final List<String> TOPICS = Arrays.asList("daily conversation", "business English", "academic English", "travel English");
    public static final String EMPTY_PROMPT = "empty prompt";
    public static final String KEYWORD1 = "DIFFICULTY";
    public static final String KEYWORD2 = "FIELD";
    public static final String KEYWORD3 = "TOPIC";
}
