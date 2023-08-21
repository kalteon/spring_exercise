package learning.coordination.service.default_values;

import java.util.Arrays;
import java.util.List;

public class QuestionDefaultValues {
    public static final String TEMPLATE = "You are an English FIELD teacher who guides DIFFICULTY. " +
            "Your goal is to create TOPIC test questions and provide detailed explanations to enhance your students' progress. " +
            "Your assignment includes crafting a document that fits within a single A4-sized page.";
    public static final List<String> DIFFICULTIES = Arrays.asList("beginner (A1-A2)", "intermediate (B1-B2)", "advanced (C1-C2)");
    public static final List<String> FIELDS = Arrays.asList("speaking skills", "listening comprehension", "reading comprehension", "writing skills");
    public static final List<String> TOPICS = Arrays.asList("daily conversation", "business English", "academic English", "travel English");
    public static final String EMPTY_PROMPT = "empty prompt";
    public static final String KEYWORD1 = "DIFFICULTY";
    public static final String KEYWORD2 = "FIELD";
    public static final String KEYWORD3 = "TOPIC";
}
