package learning.coordination.controller;

import learning.coordination.controller.default_values.ControllerDefaults;
import learning.coordination.dto.request.ModifyPromptRequest;
import learning.coordination.dto.request.SelectEnglishKeywordsRequest;
import learning.coordination.dto.response.MessageResponse;
import learning.coordination.dto.question.*;
import learning.coordination.dto.response.PromptResponse;
import learning.coordination.dto.response.TemplateResponse;
import learning.coordination.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ControllerDefaults.API_BASE_PATH)
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("template/{id}")
    public ResponseEntity<TemplateResponse> findTemplateById(@PathVariable Long id) {
        TemplateResponse template = TemplateResponse.builder()
                .template(questionService.findTemplateById(id)).build();
        return ResponseEntity.ok(template);
    }

    @GetMapping("english-keywords/{id}")
    public ResponseEntity<EnglishKeywords> findEnglishKeywords(@PathVariable Long id) {
        EnglishKeywords englishKeywords = questionService.findEnglishKeywordsById(id);
        return ResponseEntity.ok(englishKeywords);
    }

    @PostMapping("english-keywords")
    public ResponseEntity<MessageResponse> selectEnglishKeywords(@RequestBody SelectEnglishKeywordsRequest selectEnglishKeywordRequest) {
        questionService.selectPromptById(
                selectEnglishKeywordRequest.getId(),
                selectEnglishKeywordRequest.getSelectedEnglishKeywords());
        return ResponseEntity.ok(new MessageResponse(ControllerDefaults.SELECTED_ENGLISH_KEYWORDS_SUCCESS));
    }

    @GetMapping("prompt/{id}")
    public ResponseEntity<PromptResponse> findPrompt(@PathVariable Long id) {
        PromptResponse prompt = PromptResponse.builder()
                .prompt(questionService.findPromptById(id)).build();
        return ResponseEntity.ok(prompt);
    }

    @PutMapping("prompt")
    public ResponseEntity<MessageResponse> modifyPrompt(@RequestBody ModifyPromptRequest modifyPromptRequest) {
        questionService.updatePromptById(
                modifyPromptRequest.getId(),
                modifyPromptRequest.getModifiedPrompt());
        return ResponseEntity.ok(new MessageResponse(ControllerDefaults.MODIFIED_PROMPT_SUCCESS));
    }
}