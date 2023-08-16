package learning.coordination.controller;

import learning.coordination.dto.question.EnglishKeywords;
import learning.coordination.dto.question.ModifyPromptRequest;
import learning.coordination.dto.question.SelectEnglishKeywordsRequest;
import learning.coordination.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/api/template/{id}")
    public String findTemplateById(@PathVariable Long id) {
        String template = questionService.findTemplateById(id);
        return template;
    }
    @GetMapping("/api/english-keywords/{id}")
    public EnglishKeywords findEnglishKeywords(@PathVariable Long id) {
        EnglishKeywords englishKeywords = questionService.findEnglishKeywordsById(id);
        return englishKeywords;
    }
    @PostMapping("/api/english-keywords")
    public void selectEnglishKeywords(@RequestBody SelectEnglishKeywordsRequest selectEnglishKeywordRequest) {
        questionService.selectPromptById(
                selectEnglishKeywordRequest.getId(),
                selectEnglishKeywordRequest.getSelectedEnglishKeywords());
    }
    @GetMapping("/api/prompt/{id}")
    public String findPrompt(@PathVariable Long id) {
        String prompt = questionService.findPromptById(id);
        return prompt;
    }
    @PutMapping("/api/prompt")
    public void modifyPrompt(@RequestBody ModifyPromptRequest modifyPromptRequest) {
        questionService.updatePromptById(
                modifyPromptRequest.getId(),
                modifyPromptRequest.getModifiedPrompt());
    }
}
