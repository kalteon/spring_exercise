package learning.coordination.controller;

import learning.coordination.dto.gpt.CallGptRequest;
import learning.coordination.service.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GptController {

    private final GptService gptService;

    @PostMapping("/api/answer")
    public void callGpt(@RequestBody CallGptRequest callGptRequest) {
        gptService.callGpt(callGptRequest.getId());
    }
}
