package learning.coordination.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import learning.coordination.service.default_values.GptDefaultValues;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GptService {

    private final QuestionService questionService;
    private final LearningDataService learningDataService;

    public void callGpt(Long id) {
        String prompt = questionService.findPromptById(id);
        OpenAiService openAiService = new OpenAiService(GptDefaultValues.API_KEY);
        CompletionRequest request = CompletionRequest.builder()
                .prompt(prompt)
                .maxTokens(GptDefaultValues.MAX_TOKENS)
                .temperature(GptDefaultValues.TEMPERATURE)
                .build();
        CompletionResult response = openAiService.createCompletion(request);
        String answer = response.getChoices().get(0).getText();
        learningDataService.updateAnswer(id, answer);
    }
}

