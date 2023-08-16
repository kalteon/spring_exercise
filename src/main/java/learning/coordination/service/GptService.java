package learning.coordination.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GptService {

    private final QuestionService questionService;
    private final LearningDataService learningDataService;
    public void callGpt(Long id) {
        // 임시 코드 gpt api 사용으로 바꾸기
        String prompt = questionService.findPromptById(id);
        String answer = "answer: " + prompt;
        learningDataService.updateAnswer(id, answer);
    }
}
