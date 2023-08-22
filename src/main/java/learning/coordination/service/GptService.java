package learning.coordination.service;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import learning.coordination.service.api_keys.GptToken;
import learning.coordination.service.default_values.GptDefaultValues;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GptService {

    private final QuestionService questionService;
    private final LearningDataService learningDataService;

    public void callGpt(Long id) {
        String prompt = questionService.findPromptById(id);
        String answer;
        OpenAiService service = new OpenAiService(GptToken.GPT_API_TOKEN, Duration.ofSeconds(30));

        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), prompt);
        messages.add(systemMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model(GptDefaultValues.MODEL)
                .messages(messages)
                .maxTokens(GptDefaultValues.MAX_TOKENS)
                .n(1)
                .logitBias(new HashMap<>())
                .build();

        StringBuilder sentence = new StringBuilder();

        service.streamChatCompletion(chatCompletionRequest)
                .doOnError(Throwable::printStackTrace)
                .blockingForEach(chatCompletionChunk -> {
                    for (ChatCompletionChoice choice : chatCompletionChunk.getChoices()) {
                        String content = choice.getMessage().getContent();
                        sentence.append(content).append("");
                    }
                });

        service.shutdownExecutor();

        answer = sentence.toString();
        learningDataService.initLearningData(id);
        learningDataService.updateAnswer(id, answer);
    }
}
